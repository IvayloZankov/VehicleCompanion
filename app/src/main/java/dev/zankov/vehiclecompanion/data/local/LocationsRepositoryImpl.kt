package dev.zankov.vehiclecompanion.data.local

import dev.zankov.vehiclecompanion.data.local.dao.PoiDao
import dev.zankov.vehiclecompanion.data.network.LocationsApi
import dev.zankov.vehiclecompanion.model.Poi
import dev.zankov.vehiclecompanion.model.PoiEntity
import dev.zankov.vehiclecompanion.model.toPoiEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val locationsApi: LocationsApi,
    private val poiDao: PoiDao
): LocationsRepository {
    override suspend fun getPointsOfInterest(
        southWestCorner: String,
        northEastCorner: String,
        pageSize: Int
    ): Result<List<Poi>> {
        val pointsOfInterest = locationsApi.getPointsOfInterest(
            southWestCorner,
            northEastCorner,
            pageSize
        )
        if (pointsOfInterest.isSuccessful) {
            pointsOfInterest.body()?.let { locationsModel ->
                val entities = locationsModel.pois.map { it.toPoiEntity() }

                // Refresh the database
                poiDao.clearAll()
                poiDao.insertAll(entities)
                return Result.success(locationsModel.pois)
            }
        } else {
            return Result.failure(Exception("Failed to fetch points of interest"))
        }
        return Result.failure(Exception("Failed to fetch points of interest"))
    }

    override suspend fun getPointOfInterestById(id: Int): Flow<PoiEntity?> {
        return poiDao.getPoiById(id).flowOn(Dispatchers.IO)
    }
}
