package dev.zankov.vehiclecompanion.data.network

import dev.zankov.vehiclecompanion.model.LocationsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationsApi {

    /**
     * Discovers points of interest within a given geographical bounding box.
     *
     * @param southWestCorner The South-West corner of the bounding box, as a "latitude,longitude" string.
     * @param northEastCorner The North-East corner of the bounding box, as a "latitude,longitude" string.
     * @param pageSize The maximum number of results to return.
     * @return A [Response] containing a [LocationsModel] object.
     */
    @GET("pois/discover")
    suspend fun getPointsOfInterest(
        @Query("sw_corner") southWestCorner: String,
        @Query("ne_corner") northEastCorner: String,
        @Query("page_size") pageSize: Int
    ): Response<LocationsModel>
}
