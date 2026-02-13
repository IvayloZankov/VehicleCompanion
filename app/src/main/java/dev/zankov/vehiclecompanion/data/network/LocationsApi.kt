package dev.zankov.vehiclecompanion.data.network

import dev.zankov.vehiclecompanion.model.LocationsModel
import retrofit2.Response
import retrofit2.http.GET

interface LocationsApi {

    /**
     * Discovers points of interest within a given geographical bounding box.
     *
     * @return A [Response] containing a [LocationsModel] object.
     */
    @GET("getPlaces")
    suspend fun getPointsOfInterest(): Response<LocationsModel>
}
