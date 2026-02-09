package dev.zankov.vehiclecompanion.data.local

import dev.zankov.vehiclecompanion.model.Poi
import dev.zankov.vehiclecompanion.model.PoiEntity
import dev.zankov.vehiclecompanion.model.toPoiEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeLocationRepository: LocationsRepository {

    private var poisToReturn: List<Poi> = fakePoiList

    private var exceptionToThrow: Exception? = null

    override suspend fun getPointsOfInterest(
        southWestCorner: String,
        northEastCorner: String,
        pageSize: Int
    ): Result<List<Poi>> {
        exceptionToThrow?.let { throw it }
        return Result.success(poisToReturn)
    }

    override suspend fun getPointOfInterestById(id: Int): Flow<PoiEntity?> {
        exceptionToThrow?.let { throw it }
        return flowOf(fakePoiList.getOrNull(id)?.toPoiEntity())
    }

    fun setPois(pois: List<Poi>) {
        this.poisToReturn = pois
    }

    fun setException(exception: Exception) {
        this.exceptionToThrow = exception

    }
}