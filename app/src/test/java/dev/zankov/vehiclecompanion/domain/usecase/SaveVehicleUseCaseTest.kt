package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.repository.FakeVehicleRepository
import dev.zankov.vehiclecompanion.domain.model.Vehicle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class SaveVehicleUseCaseTest {

    private lateinit var vehicleRepository: FakeVehicleRepository
    private lateinit var saveVehicleUseCase: SaveVehicleUseCase

    @Before
    fun setUp() {
        vehicleRepository = FakeVehicleRepository()
        saveVehicleUseCase = SaveVehicleUseCase(vehicleRepository)
    }

    @Test
    fun `invoke WHEN repository succeeds THEN vehicle is saved`() = runTest {
        val newVehicle = Vehicle(
            id = 4,
            name = "New Car",
            make = "Tesla",
            model = "Model 3",
            vin = "XYZ789LMN",
            fuelType = "Electric"
        )

        saveVehicleUseCase(newVehicle)

        val vehicles = vehicleRepository.getVehiclesStream().first()
        Assert.assertTrue("Vehicle should have been saved", vehicles.contains(newVehicle))
    }

    @Test
    fun `invoke WHEN repository fails THEN exception is thrown`() = runTest {
        val newVehicle = Vehicle(
            id = 4,
            name = "New Car",
            make = "Tesla",
            model = "Model 3",
            vin = "XYZ789LMN",
            fuelType = "Electric"
        )
        val expectedException = IOException("Database connection failed")
        vehicleRepository.setException(expectedException)
        var capturedException: Throwable? = null

        try {
            saveVehicleUseCase(newVehicle)
        } catch (e: Exception) {
            capturedException = e
        }

        Assert.assertEquals("invoke should have thrown the correct exception", expectedException, capturedException)
    }
}
