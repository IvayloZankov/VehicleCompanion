package dev.zankov.vehiclecompanion.ui.garage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.zankov.vehiclecompanion.R
import dev.zankov.vehiclecompanion.ui.theme.VehicleCompanionTheme

@Composable
fun UpdateVehicleFragment(
    viewModel: GarageViewModel = hiltViewModel(),
    onCloseClick: () -> Unit,
    onSaveClick: () -> Unit

) {
    var name by rememberSaveable { mutableStateOf("") }
    var make by rememberSaveable { mutableStateOf("") }
    var model by rememberSaveable { mutableStateOf("") }
    var vin by rememberSaveable { mutableStateOf("") }
    var fuelType by rememberSaveable { mutableStateOf("") }

    UpdateVehicleScreen(
        name = name,
        onNameChange = { name = it },
        make = make,
        onMakeChange = { make = it },
        model = model,
        onModelChange = { model = it },
        vin = vin,
        onVinChange = { vin = it },
        fuelType = fuelType,
        onFuelTypeChange = { fuelType = it },
        onCloseClick = onCloseClick,
        onSaveClick = onSaveClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateVehicleScreen(
    name: String,
    onNameChange: (String) -> Unit,
    make: String,
    onMakeChange: (String) -> Unit,
    model: String,
    onModelChange: (String) -> Unit,
    vin: String,
    onVinChange: (String) -> Unit,
    fuelType: String,
    onFuelTypeChange: (String) -> Unit,
    onCloseClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.padding(16.dp),
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Add Vehicle",
                        Modifier.padding(horizontal = 16.dp)
                    )
                    IconButton(onClick = onCloseClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close_24),
                            contentDescription = "Close")
                    }
                }
                OutlinedTextField(
                    value = name,
                    onValueChange = onNameChange,
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = make,
                    onValueChange = onMakeChange,
                    label = { Text("Make") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = model,
                    onValueChange = onModelChange,
                    label = { Text("Model") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = vin,
                    onValueChange = onVinChange,
                    label = { Text("VIN") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = fuelType,
                    onValueChange = onFuelTypeChange,
                    label = { Text("Fuel Type") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Button(
                onClick = onSaveClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Text("Save")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UpdateVehicleScreenPreview() {
    VehicleCompanionTheme {
        UpdateVehicleScreen(
            name = "My Awesome Car",
            onNameChange = {},
            make = "Toyota",
            onMakeChange = {},
            model = "GR Yaris",
            onModelChange = {},
            vin = "1234567890",
            onVinChange = {},
            fuelType = "Gasoline",
            onFuelTypeChange = {},
            onCloseClick = {},
            onSaveClick = {}
        )
    }
}