package dev.zankov.vehiclecompanion.data.local

import dev.zankov.vehiclecompanion.model.Vehicle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeVehicleRepository : VehicleRepository {

    private var vehicles = fakeVehicles.toMutableList()
    private var exceptionToThrow: Exception? = null

    override fun getVehiclesStream(): Flow<List<Vehicle>> {
        exceptionToThrow?.let { throw it }
        return flowOf(vehicles)
    }

    override fun getVehicleStream(id: Int): Flow<Vehicle?> {
        exceptionToThrow?.let { throw it }
        return flowOf(vehicles.find { it.id == id })
    }

    override suspend fun insertVehicle(vehicle: Vehicle) {
        exceptionToThrow?.let { throw it }
        vehicles.add(vehicle)
    }

    override suspend fun deleteVehicle(vehicle: Vehicle) {
        exceptionToThrow?.let { throw it }
        vehicles.remove(vehicle)
    }

    fun setVehicles(vehicles: List<Vehicle>) {
        this.vehicles = vehicles.toMutableList()
    }

    fun setException(exception: Exception) {
        this.exceptionToThrow = exception
    }
}
