package dev.zankov.vehiclecompanion.domain

import dev.zankov.vehiclecompanion.domain.model.Vehicle
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    fun getVehiclesStream(): Flow<List<Vehicle>>
    fun getVehicleStream(id: Int): Flow<Vehicle?>
    suspend fun insertVehicle(vehicle: Vehicle)
    suspend fun deleteVehicle(vehicle: Vehicle)
}
