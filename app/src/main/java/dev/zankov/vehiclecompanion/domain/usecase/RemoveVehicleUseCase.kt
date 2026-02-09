package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.local.VehicleRepository
import dev.zankov.vehiclecompanion.model.Vehicle
import javax.inject.Inject

/**
 * A use case that removes a vehicle from the local database.
 *
 * @param repository The repository for vehicle data operations.
 */
class RemoveVehicleUseCase @Inject constructor(
    private val repository: VehicleRepository
) {

    /**
     * Removes a vehicle from the local repository.
     *
     * @param vehicle The [Vehicle] object to be removed.
     */
    suspend operator fun invoke(vehicle: Vehicle) {
        repository.deleteVehicle(vehicle)
    }
}
