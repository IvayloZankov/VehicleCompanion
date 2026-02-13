package dev.zankov.vehiclecompanion.model


import com.google.gson.annotations.SerializedName

data class Poi(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("loc")
    val loc: List<Double> = listOf(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("category")
    val categoryName: String = "",
    @SerializedName("rating")
    val rating: Double = 0.0,
    @SerializedName("url")
    val url: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("large_image_url")
    val largeImageUrl: String = ""
)
