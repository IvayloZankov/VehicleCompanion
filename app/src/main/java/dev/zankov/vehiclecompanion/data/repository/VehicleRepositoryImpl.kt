package dev.zankov.vehiclecompanion.data.repository

import dev.zankov.vehiclecompanion.data.local.VehicleDatabase
import dev.zankov.vehiclecompanion.data.local.entity.toDomain
import dev.zankov.vehiclecompanion.data.local.entity.toEntity
import dev.zankov.vehiclecompanion.domain.VehicleRepository
import dev.zankov.vehiclecompanion.domain.model.Vehicle
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.collections.map

/**
 * Concrete implementation of [VehicleRepository] that interacts with the local [VehicleDatabase].
 * This class is responsible for managing vehicle data in the application's local storage.
 * It uses [Dispatchers.IO] to provide asynchronous data streams and operations.
 *
 * @property vehicleDatabase The Room database instance used to access vehicle data.
 */
class VehicleRepositoryImpl @Inject constructor(
    private val vehicleDatabase: VehicleDatabase
) : VehicleRepository {

    override fun getVehiclesStream(): Flow<List<Vehicle>> {
        return vehicleDatabase.vehicleDao().selectAllVehicles()
            .map { entities -> entities.map { it.toDomain() } }
            .flowOn(Dispatchers.IO)
    }

    override fun getVehicleStream(id: Int): Flow<Vehicle?> {
        return vehicleDatabase.vehicleDao().getVehicleById(id)
            .map { it?.toDomain() }.flowOn(Dispatchers.IO)
    }

    override suspend fun insertVehicle(vehicle: Vehicle) {
        withContext(Dispatchers.IO) {
            vehicleDatabase.vehicleDao().insertVehicle(vehicle.toEntity())
        }
    }

    override suspend fun deleteVehicle(vehicle: Vehicle) {
        withContext(Dispatchers.IO) {
            vehicleDatabase.vehicleDao().deleteVehicle(vehicle.toEntity())
        }
    }
}
