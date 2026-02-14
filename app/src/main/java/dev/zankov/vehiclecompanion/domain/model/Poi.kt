package dev.zankov.vehiclecompanion.domain.model

data class Poi(
    val id: Int,
    val name: String,
    val category: String,
    val location: List<Double>,
    val rating: Double,
    val url: String,
    val image: String
)
