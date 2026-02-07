package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.local.VehicleRepository
import dev.zankov.vehiclecompanion.model.Vehicle
import javax.inject.Inject

class SaveVehicleUseCase @Inject constructor(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(
        vehicle: Vehicle
    ) {
        repository.insertVehicle(vehicle)
    }
}
