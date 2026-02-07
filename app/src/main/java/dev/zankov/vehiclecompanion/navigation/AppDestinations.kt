package dev.zankov.vehiclecompanion.navigation

import dev.zankov.vehiclecompanion.R
import dev.zankov.vehiclecompanion.navigation.AppRoutes.ROUTE_GARAGE
import dev.zankov.vehiclecompanion.navigation.AppRoutes.ROUTE_PLACES

object AppRoutes {
    const val ROUTE_GARAGE = "garage_route"
    const val ROUTE_PLACES = "places_route"

    const val ARG_VEHICLE_ID = "vehicleId"
    const val ROUTE_UPDATE_VEHICLE = "update_vehicle_route/{$ARG_VEHICLE_ID}"

    fun updateVehicleRoute(vehicleId: Int) = "update_vehicle_route/$vehicleId"
}

enum class AppDestinations(
    val route: String,
    val label: String,
    val iconResource: Int,
) {
    GARAGE(ROUTE_GARAGE, "Garage", R.drawable.ic_garage),
    PLACES(ROUTE_PLACES, "Places", R.drawable.ic_explore)
}