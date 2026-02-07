package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.local.VehicleRepository
import dev.zankov.vehiclecompanion.model.Vehicle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class FetchVehiclesUseCase @Inject constructor(
    private val repository: VehicleRepository
) {
    operator fun invoke(
        scope: CoroutineScope,
        onSuccess: (List<Vehicle>) -> Unit,
        onFailure: (Throwable) -> Unit
    ): Job {
        return scope.launch {
            repository.getVehiclesStream().collect { listVehicles: List<Vehicle> ->
                onSuccess(listVehicles)
            }
        }
    }
}
