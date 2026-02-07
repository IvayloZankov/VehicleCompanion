package dev.zankov.vehiclecompanion.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.zankov.vehiclecompanion.ui.garage.GarageFragment
import dev.zankov.vehiclecompanion.ui.garage.UpdateVehicleFragment
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
                    navController.navigate(AppRoutes.ROUTE_UPDATE_VEHICLE)
                }
            )
        }
        composable(AppRoutes.ROUTE_UPDATE_VEHICLE) {
            UpdateVehicleFragment(
                onCloseClick = {
                    navController.popBackStack()
                },
                onSaveClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(AppRoutes.ROUTE_PLACES) {
            PlacesFragment()
        }
    }
}
