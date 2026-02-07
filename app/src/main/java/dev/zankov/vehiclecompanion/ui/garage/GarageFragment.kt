package dev.zankov.vehiclecompanion.ui.garage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.zankov.vehiclecompanion.R
import dev.zankov.vehiclecompanion.model.Vehicle
import dev.zankov.vehiclecompanion.ui.theme.VehicleCompanionTheme

@Composable
fun GarageFragment(
    modifier: Modifier = Modifier,
    viewModel: GarageViewModel = hiltViewModel(),
    onAddVehicleClick: () -> Unit
) {
    val stateVehicles by viewModel.stateFlowVehicles.collectAsState(initial = emptyList())

    GarageScreen(
        modifier = modifier,
        stateVehicles = stateVehicles,
        onAddVehicleClick = onAddVehicleClick
    )
}

@Composable
fun GarageScreen(
    modifier: Modifier = Modifier,
    stateVehicles: List<Vehicle>,
    onAddVehicleClick: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = onAddVehicleClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_vehicle),
                    contentDescription = "Add new vehicle"
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center,
        ) {
            if (stateVehicles.isEmpty()) {
                Button(
                    onClick = onAddVehicleClick,
                    modifier = Modifier
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(
                            vertical = 16.dp,
                            horizontal = 32.dp
                        )

                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add_vehicle),
                            contentDescription = "Add new vehicle",
                            modifier = Modifier.size(72.dp)
                        )
                        Text(
                            text = "Add new vehicle",
                            style = MaterialTheme.typography.headlineSmall,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                }


            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(stateVehicles) { vehicle ->
                        VehicleCard(vehicle = vehicle)
                    }
                }
            }
        }
    }
}

@Composable
fun VehicleCard(vehicle: Vehicle) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = vehicle.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "${vehicle.make} ${vehicle.model}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = vehicle.fuelType,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GaragePreviewEmpty() {
    VehicleCompanionTheme {
        GarageScreen(
            stateVehicles = emptyList(),
            onAddVehicleClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GaragePreview() {
    val sampleVehicles = listOf(
        Vehicle(
            name = "My Car",
            make = "Toyota",
            model = "Camry",
            vin = 123456789,
            fuelType = "Gasoline"
        ),
        Vehicle(
            name = "Work Van",
            make = "Ford",
            model = "Transit",
            vin = 987654321,
            fuelType = "Diesel"
        )
    )

    VehicleCompanionTheme {
        GarageScreen(
            stateVehicles = sampleVehicles,
            onAddVehicleClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VehicleCardPreview() {
    val sampleVehicle = Vehicle(
        name = "My Car",
        make = "Toyota",
        model = "Camry",
        vin = 123456789,
        fuelType = "Gasoline"
    )
    VehicleCompanionTheme {
        VehicleCard(vehicle = sampleVehicle)
    }
}
