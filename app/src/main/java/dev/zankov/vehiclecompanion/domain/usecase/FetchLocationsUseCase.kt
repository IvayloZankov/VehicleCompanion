package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.local.LocationsRepository
import dev.zankov.vehiclecompanion.model.Poi
import javax.inject.Inject

class FetchLocationsUseCase @Inject constructor(
    private val locationsRepository: LocationsRepository
) {
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
