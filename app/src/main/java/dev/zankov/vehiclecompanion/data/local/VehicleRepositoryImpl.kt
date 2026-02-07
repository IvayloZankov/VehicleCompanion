package dev.zankov.vehiclecompanion.data.local

import dev.zankov.vehiclecompanion.model.Vehicle
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class VehicleRepositoryImpl @Inject constructor(
    private val vehicleDatabase: VehicleDatabase
) : VehicleRepository {

    override fun getVehiclesStream(): Flow<List<Vehicle>> {
        return vehicleDatabase.vehicleDao().selectAllVehicles().flowOn(Dispatchers.IO)
    }

    override suspend fun insertVehicle(vehicle: Vehicle) {
        withContext(Dispatchers.IO) {
            vehicleDatabase.vehicleDao().insertVehicle(vehicle)
        }
    }

    override suspend fun deleteVehicle(vehicle: Vehicle) {
        withContext(Dispatchers.IO) {
            vehicleDatabase.vehicleDao().deleteVehicle(vehicle)
        }
    }
}
