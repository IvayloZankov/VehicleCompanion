package dev.zankov.vehiclecompanion.data.remote.dto

import com.google.gson.annotations.SerializedName
import dev.zankov.vehiclecompanion.data.remote.dto.PoiDto

data class LocationsModel(
    @SerializedName("data") val pois: List<PoiDto> = listOf()
)