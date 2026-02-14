package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.domain.VehicleRepository
import dev.zankov.vehiclecompanion.domain.model.Vehicle
import javax.inject.Inject

/**
 * Fetches a stream of all vehicles from the repository.
 *
 * @param repository The data source for vehicles.
 */
class FetchVehiclesUseCase @Inject constructor(
    private val repository: VehicleRepository
) {

    /**
     * Executes the use case to fetch a stream of vehicles.
     *
     * @param onSuccess Invoked with the list of vehicles each time the stream emits.
     * @param onFailure Invoked if an error occurs during the collection.
     */
    suspend operator fun invoke(
        onSuccess: (List<Vehicle>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        repository.getVehiclesStream().collect {
            onSuccess(it)
        }
    }
}
