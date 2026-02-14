package dev.zankov.vehiclecompanion.data.remote.dto

import com.google.gson.annotations.SerializedName
import dev.zankov.vehiclecompanion.data.local.entity.PoiEntity
import dev.zankov.vehiclecompanion.domain.model.Poi

data class PoiDto(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("category") val categoryName: String = "",
    @SerializedName("loc") val loc: List<Double> = listOf(),
    @SerializedName("rating") val rating: Double = 0.0,
    @SerializedName("url") val url: String = "",
    @SerializedName("image") val image: String = "",
    @SerializedName("large_image_url") val largeImageUrl: String = ""
)

fun PoiDto.toDomain(): Poi {
    return Poi(
        id = id,
        name = name,
        category = categoryName,
        location = loc,
        rating = rating,
        url = url,
        image = image
    )
}

fun PoiDto.toEntity(): PoiEntity {
    return PoiEntity(
        id = id,
        loc = loc,
        name = name,
        categoryName = categoryName,
        rating = rating,
        url = url,
        v320x320Url = image,
        largeImageUrl = largeImageUrl
    )
}
