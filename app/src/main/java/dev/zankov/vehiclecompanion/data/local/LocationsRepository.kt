package dev.zankov.vehiclecompanion.data.local

import dev.zankov.vehiclecompanion.model.Poi
import dev.zankov.vehiclecompanion.model.PoiEntity
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {
    suspend fun getPointsOfInterest(
        southWestCorner: String,
        northEastCorner: String,
        pageSize: Int
    ): Result<List<Poi>>
    suspend fun getPointOfInterestById(id: Int): Flow<PoiEntity?>
}