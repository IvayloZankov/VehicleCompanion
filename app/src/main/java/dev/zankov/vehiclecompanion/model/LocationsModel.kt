package dev.zankov.vehiclecompanion.model


import com.google.gson.annotations.SerializedName

data class LocationsModel(
    @SerializedName("pois")
    val pois: List<Poi> = listOf()
)
