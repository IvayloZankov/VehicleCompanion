package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.local.VehicleRepository
import dev.zankov.vehiclecompanion.model.Vehicle
import javax.inject.Inject

/**
 * A use case that retrieves a specific vehicle by its ID from the repository.
 *
 * @property repository The data source for vehicles.
 */
class GetVehicleUseCase @Inject constructor(
    private val repository: VehicleRepository
) {

    /**
     * Observes a vehicle stream by its [id].
     *
     * @param id The unique identifier of the vehicle to retrieve.
     * @param onSuccess Callback invoked with the [Vehicle] object (or null if not found).
     * @param onFailure Callback invoked on retrieval error.
     */
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
