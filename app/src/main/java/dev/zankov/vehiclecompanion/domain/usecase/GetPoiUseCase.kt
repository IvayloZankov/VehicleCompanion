package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.local.LocationsRepository
import dev.zankov.vehiclecompanion.model.Poi
import dev.zankov.vehiclecompanion.model.toPoi
import javax.inject.Inject

/**
 * Use case for retrieving a specific Point of Interest (POI) by its ID.
 *
 * @param repository The [LocationsRepository] for fetching POI data.
 */
class GetPoiUseCase @Inject constructor(
    private val repository: LocationsRepository
) {

    /**
     * Retrieves a Point of Interest (POI) by its ID.
     *
     * @param id The unique identifier of the POI.
     * @param onSuccess Callback invoked with the found [Poi] (or null if not found).
     * @param onFailure Callback invoked if an error occurs.
     */
    suspend operator fun invoke(
        id: Int,
        onSuccess: (Poi?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        runCatching {
            repository.getPointOfInterestById(id).collect { poiEntity ->
                onSuccess(poiEntity?.toPoi())
            }
        }.onFailure { exception ->
            onFailure(exception)
        }
    }
}
