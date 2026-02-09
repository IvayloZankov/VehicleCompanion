package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.local.LocationsRepository
import dev.zankov.vehiclecompanion.model.Poi
import javax.inject.Inject

/**
 * A use case that fetches points of interest (POIs) within a given geographical bounding box.
 *
 * @property locationsRepository The repository for accessing location data.
 */
class FetchLocationsUseCase @Inject constructor(
    private val locationsRepository: LocationsRepository
) {

    /**
     * Fetches points of interest (POIs) within a given bounding box.
     *
     * @param southWestCorner The south-west corner of the bounding box.
     * @param northEastCorner The north-east corner of the bounding box.
     * @param pageSize The maximum number of POIs to fetch.
     * @param onSuccess Callback invoked with the list of [Poi]s on success.
     * @param onFailure Callback invoked with the [Throwable] on failure.
     */
    suspend operator fun invoke(
        southWestCorner: String,
        northEastCorner: String,
        pageSize: Int,
        onSuccess: (List<Poi>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val pointsOfInterest = locationsRepository.getPointsOfInterest(
            southWestCorner,
            northEastCorner,
            pageSize
        )
        pointsOfInterest
            .onSuccess { pois -> onSuccess(pois) }
            .onFailure { exception -> onFailure(exception) }
    }
}
