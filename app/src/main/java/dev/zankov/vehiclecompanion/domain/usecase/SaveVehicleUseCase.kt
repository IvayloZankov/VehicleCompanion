package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.local.VehicleRepository
import dev.zankov.vehiclecompanion.model.Vehicle
import javax.inject.Inject

/**
 * Use case for saving a [Vehicle] to the local repository.
 *
 * @param repository The repository for vehicle data operations.
 */
class SaveVehicleUseCase @Inject constructor(
    private val repository: VehicleRepository
) {

    /**
     * Saves the given [Vehicle] to the local repository.
     *
     * @param vehicle The vehicle to save.
     */
    suspend operator fun invoke(
        vehicle: Vehicle
    ) {
        repository.insertVehicle(vehicle)
    }
}
