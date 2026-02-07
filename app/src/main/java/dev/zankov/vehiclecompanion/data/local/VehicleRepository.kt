package dev.zankov.vehiclecompanion.data.local

import dev.zankov.vehiclecompanion.model.Vehicle
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    fun getVehiclesStream(): Flow<List<Vehicle>>
}