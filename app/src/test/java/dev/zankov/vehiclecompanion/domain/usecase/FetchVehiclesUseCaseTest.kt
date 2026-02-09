package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.local.FakeVehicleRepository
import dev.zankov.vehiclecompanion.data.local.fakeVehicles
import dev.zankov.vehiclecompanion.model.Vehicle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class FetchVehiclesUseCaseTest {

    private lateinit var vehicleRepository: FakeVehicleRepository
    private lateinit var fetchVehiclesUseCase: FetchVehiclesUseCase

    @Before
    fun setUp() {
        vehicleRepository = FakeVehicleRepository()
        fetchVehiclesUseCase = FetchVehiclesUseCase(vehicleRepository)
    }

    @Test
    fun `invoke WHEN repository succeeds THEN onSuccess is called with vehicle list`() = runTest {
        val expectedVehicleList = fakeVehicles
        vehicleRepository.setVehicles(expectedVehicleList)

        var capturedVehicleList: List<Vehicle>? = null
        var capturedException: Throwable? = null

        try {
            fetchVehiclesUseCase(
                onSuccess = { list -> capturedVehicleList = list },
                onFailure = { ex -> capturedException = ex }
            )
        } catch (e: Exception) {
            capturedException = e
        }

        Assert.assertEquals("onSuccess should have received the correct vehicle list", expectedVehicleList, capturedVehicleList)
        Assert.assertNull("onFailure should not have been called", capturedException)
    }

    @Test
    fun `invoke WHEN repository fails THEN onFailure is not called and exception is thrown`() = runTest {
        val expectedException = IOException("Database connection failed")
        vehicleRepository.setException(expectedException)

        var capturedVehicleList: List<Vehicle>? = null
        var capturedException: Throwable? = null

        try {
            fetchVehiclesUseCase(
                onSuccess = { list -> capturedVehicleList = list },
                onFailure = { ex -> capturedException = ex }
            )
        } catch (e: Exception) {
            capturedException = e
        }

        Assert.assertNull("onSuccess should not have been called", capturedVehicleList)
        Assert.assertEquals("invoke should have thrown the correct exception", expectedException, capturedException)
    }
}
