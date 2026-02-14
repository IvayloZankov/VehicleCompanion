package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.repository.FakeVehicleRepository
import dev.zankov.vehiclecompanion.data.repository.fakeVehicles
import dev.zankov.vehiclecompanion.domain.model.Vehicle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class GetVehicleUseCaseTest {

    private lateinit var vehicleRepository: FakeVehicleRepository
    private lateinit var getVehicleUseCase: GetVehicleUseCase

    @Before
    fun setUp() {
        vehicleRepository = FakeVehicleRepository()
        getVehicleUseCase = GetVehicleUseCase(vehicleRepository)
    }

    @Test
    fun `invoke WHEN repository succeeds THEN onSuccess is called with vehicle`() = runTest {
        val expectedVehicle = fakeVehicles.first()
        var capturedVehicle: Vehicle? = null
        var capturedException: Throwable? = null

        getVehicleUseCase(
            id = expectedVehicle.id,
            onSuccess = { vehicle -> capturedVehicle = vehicle },
            onFailure = { ex -> capturedException = ex }
        )

        Assert.assertEquals("onSuccess should have received the correct vehicle", expectedVehicle, capturedVehicle)
        Assert.assertNull("onFailure should not have been called", capturedException)
    }

    @Test
    fun `invoke WHEN vehicle not found THEN onSuccess is called with null`() = runTest {
        val nonExistentId = 999
        var capturedVehicle: Vehicle? = fakeVehicles.first() // Non-null initial value
        var capturedException: Throwable? = null

        getVehicleUseCase(
            id = nonExistentId,
            onSuccess = { vehicle -> capturedVehicle = vehicle },
            onFailure = { ex -> capturedException = ex }
        )

        Assert.assertNull("onSuccess should have been called with null", capturedVehicle)
        Assert.assertNull("onFailure should not have been called", capturedException)
    }

    @Test
    fun `invoke WHEN repository fails THEN onFailure is called with exception`() = runTest {
        val expectedException = IOException("Database connection failed")
        vehicleRepository.setException(expectedException)
        val vehicleId = 1
        var capturedVehicle: Vehicle? = null
        var capturedException: Throwable? = null

        try {
            getVehicleUseCase(
                id = vehicleId,
                onSuccess = { vehicle -> capturedVehicle = vehicle },
                onFailure = { ex -> capturedException = ex }
            )
        } catch (e: Exception) {
            capturedException = e
        }

        Assert.assertNull("onSuccess should not have been called", capturedVehicle)
        Assert.assertEquals("invoke should have thrown the correct exception", expectedException, capturedException)
    }
}
