package dev.zankov.vehiclecompanion.domain.model

data class Vehicle(
    val id: Int = 0,
    val name: String,
    val make: String,
    val model: String,
    val vin: String,
    val fuelType: String
)
