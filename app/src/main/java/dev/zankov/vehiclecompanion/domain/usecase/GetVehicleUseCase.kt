package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.local.VehicleRepository
import dev.zankov.vehiclecompanion.model.Vehicle
import javax.inject.Inject

class GetVehicleUseCase @Inject constructor(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(
        id: Int,
        onSuccess: (Vehicle?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        repository.getVehicleStream(id).collect { vehicle ->
                onSuccess(vehicle)
        }
    }
}
