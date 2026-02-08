package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.network.LocationsApi
import dev.zankov.vehiclecompanion.model.Poi
import javax.inject.Inject

class FetchLocationsUseCase @Inject constructor(
    private val locationsApi: LocationsApi
) {
    suspend operator fun invoke(
        southWestCorner: String,
        northEastCorner: String,
        pageSize: Int,
        onSuccess: (List<Poi>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val pointsOfInterest = locationsApi.discoverPointsOfInterest(
            southWestCorner,
            northEastCorner,
            pageSize
        )
        if (pointsOfInterest.isSuccessful) {
            pointsOfInterest.body()?.let { locationsModel ->
                onSuccess(locationsModel.pois)
            }
        } else {
            onFailure(Exception("Failed to fetch points of interest"))
        }
    }
}
