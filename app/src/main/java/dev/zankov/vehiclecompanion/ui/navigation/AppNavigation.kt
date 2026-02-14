package dev.zankov.vehiclecompanion.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.zankov.vehiclecompanion.ui.garage.GarageFragment
import dev.zankov.vehiclecompanion.ui.garage.UpdateVehicleFragment
import dev.zankov.vehiclecompanion.ui.places.PlacesDetailsFragment
import dev.zankov.vehiclecompanion.ui.places.PlacesFragment

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.ROUTE_GARAGE,
        modifier = modifier
    ) {
        composable(AppRoutes.ROUTE_GARAGE) {
            GarageFragment(
                onAddVehicleClick = {
                    navController.navigate(AppRoutes.updateVehicleRoute(-1))
                },
                onEditVehicleClick = { vehicleId ->
                    navController.navigate(AppRoutes.updateVehicleRoute(vehicleId))
                }
            )
        }
        composable(
            route = AppRoutes.ROUTE_UPDATE_VEHICLE,
            arguments = listOf(
                navArgument(AppRoutes.ARG_VEHICLE_ID) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            UpdateVehicleFragment(
                onCloseClick = { navController.popBackStack() },
                onSaveClick = { navController.popBackStack() },
                onDeleteClick = { navController.popBackStack() }
            )
        }
        composable(AppRoutes.ROUTE_PLACES) {
            PlacesFragment(
                onPoiClick = { poiId ->
                    navController.navigate(AppRoutes.placeDetailsRoute(poiId))
                }
            )
        }
        composable(
            route = AppRoutes.ROUTE_PLACE_DETAILS,
            arguments = listOf(
                navArgument(AppRoutes.ARG_POI_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            PlacesDetailsFragment()
        }
    }
}
