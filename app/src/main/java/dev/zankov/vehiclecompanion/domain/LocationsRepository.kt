package dev.zankov.vehiclecompanion.domain

import dev.zankov.vehiclecompanion.domain.model.Poi
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {
    suspend fun getPointsOfInterest(
        southWestCorner: String,
        northEastCorner: String,
        pageSize: Int
    ): Result<List<Poi>>
    suspend fun getPointOfInterestById(id: Int): Flow<Poi?>
}