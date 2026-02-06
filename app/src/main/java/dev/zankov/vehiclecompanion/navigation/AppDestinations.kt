package dev.zankov.vehiclecompanion.navigation

import dev.zankov.vehiclecompanion.R

enum class AppDestinations(
    val label: String,
    val iconResource: Int,
) {
    GARAGE("Garage", R.drawable.ic_garage),
    PLACES("Places", R.drawable.ic_explore)
}