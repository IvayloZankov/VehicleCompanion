package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.local.FakeVehicleRepository
import dev.zankov.vehiclecompanion.data.local.fakeVehicles
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class RemoveVehicleUseCaseTest {

    private lateinit var vehicleRepository: FakeVehicleRepository
    private lateinit var removeVehicleUseCase: RemoveVehicleUseCase

    @Before
    fun setUp() {
        vehicleRepository = FakeVehicleRepository()
        removeVehicleUseCase = RemoveVehicleUseCase(vehicleRepository)
    }

    @Test
    fun `invoke WHEN repository succeeds THEN vehicle is removed`() = runTest {
        val vehicleToRemove = fakeVehicles.first()

        removeVehicleUseCase(vehicleToRemove)

        val vehicles = vehicleRepository.getVehiclesStream().first()
        Assert.assertFalse("Vehicle should have been removed", vehicles.contains(vehicleToRemove))
    }

    @Test
    fun `invoke WHEN repository fails THEN exception is thrown`() = runTest {
        val vehicleToRemove = fakeVehicles.first()
        val expectedException = IOException("Database connection failed")
        vehicleRepository.setException(expectedException)
        var capturedException: Throwable? = null

        try {
            removeVehicleUseCase(vehicleToRemove)
        } catch (e: Exception) {
            capturedException = e
        }

        Assert.assertEquals("invoke should have thrown the correct exception", expectedException, capturedException)
    }
}
