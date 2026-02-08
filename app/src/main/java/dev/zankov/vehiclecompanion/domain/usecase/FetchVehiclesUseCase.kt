package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.local.VehicleRepository
import dev.zankov.vehiclecompanion.model.Vehicle
import javax.inject.Inject

class FetchVehiclesUseCase @Inject constructor(
    private val repository: VehicleRepository
) {
    suspend operator fun invoke(
        onSuccess: (List<Vehicle>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        repository.getVehiclesStream().collect {
            onSuccess(it)
        }
    }
}
