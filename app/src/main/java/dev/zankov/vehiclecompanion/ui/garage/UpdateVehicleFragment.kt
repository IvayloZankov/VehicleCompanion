package dev.zankov.vehiclecompanion.ui.garage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.zankov.vehiclecompanion.R
import dev.zankov.vehiclecompanion.model.Vehicle
import dev.zankov.vehiclecompanion.ui.theme.VehicleCompanionTheme

@Composable
fun UpdateVehicleFragment(
    viewModel: UpdateVehicleViewModel = hiltViewModel(),
    onCloseClick: () -> Unit,
    onSaveClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    val selectedVehicle by viewModel.stateFlowSelectedVehicle.collectAsState()

    var name by rememberSaveable { mutableStateOf("") }
    var isNameError by rememberSaveable { mutableStateOf(false) }
    var make by rememberSaveable { mutableStateOf("") }
    var isMakeError by rememberSaveable { mutableStateOf(false) }
    var model by rememberSaveable { mutableStateOf("") }
    var isModelError by rememberSaveable { mutableStateOf(false) }
    var vin by rememberSaveable { mutableStateOf("") }
    var fuelType by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(selectedVehicle) {
        name = selectedVehicle.name
        make = selectedVehicle.make
        model = selectedVehicle.model
        vin = selectedVehicle.vin
        fuelType = selectedVehicle.fuelType
    }

    fun validate(): Boolean {
        isNameError = name.isBlank()
        isMakeError = make.isBlank()
        isModelError = model.isBlank()
        return !isNameError && !isMakeError && !isModelError
    }

    UpdateVehicleScreen(
        vehicle = selectedVehicle,
        name = name,
        onNameChange = {
            name = it
            isNameError = false
        },
        isNameError = isNameError,
        make = make,
        onMakeChange = {
            make = it
            isMakeError = false
        },
        isMakeError = isMakeError,
        model = model,
        onModelChange = {
            model = it
            isModelError = false
        },
        isModelError = isModelError,
        vin = vin,
        onVinChange = { vin = it },
        fuelType = fuelType,
        onFuelTypeChange = { fuelType = it },
        onCloseClick = onCloseClick,
        onSaveClick = {
            if (validate()) {
                val vehicle = selectedVehicle.copy(
                    name = name,
                    make = make,
                    model = model,
                    vin = vin,
                    fuelType = fuelType
                )
                viewModel.saveVehicle(vehicle)
                onSaveClick()
            }
        },
        onDeleteClick = {
            viewModel.deleteVehicle(selectedVehicle)
            onDeleteClick()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateVehicleScreen(
    vehicle: Vehicle,
    name: String,
    onNameChange: (String) -> Unit,
    isNameError: Boolean,
    make: String,
    onMakeChange: (String) -> Unit,
    isMakeError: Boolean,
    model: String,
    onModelChange: (String) -> Unit,
    isModelError: Boolean,
    vin: String,
    onVinChange: (String) -> Unit,
    fuelType: String,
    onFuelTypeChange: (String) -> Unit,
    onCloseClick: () -> Unit,
    onSaveClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.padding(16.dp),
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "Add Vehicle",
                        Modifier.padding(horizontal = 16.dp)
                    )
                    Button(
                        onClick = onCloseClick,
                        modifier = Modifier.size(40.dp),   // 1. Set a fixed size
                        shape = CircleShape,               // 2. Make it circular
                        contentPadding = PaddingValues(0.dp) // 3. Remove default padding
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close_24),
                            contentDescription = "Close"
                        )
                    }
                }
                OutlinedTextField(
                    value = name,
                    onValueChange = onNameChange,
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = isNameError,
                )
                OutlinedTextField(
                    value = make,
                    onValueChange = onMakeChange,
                    label = { Text("Make") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = isMakeError,
                )
                OutlinedTextField(
                    value = model,
                    onValueChange = onModelChange,
                    label = { Text("Model") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = isModelError,
                )
                OutlinedTextField(
                    value = vin,
                    onValueChange = onVinChange,
                    label = { Text("VIN") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = fuelType,
                    onValueChange = onFuelTypeChange,
                    label = { Text("Fuel Type") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Button(
                    onClick = onSaveClick,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Save")
                }
                if (vehicle.id != 0) {
                    Button(
                        onClick = onDeleteClick,
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error,
                            contentColor = MaterialTheme.colorScheme.onError
                        )
                    ) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UpdateVehicleScreenPreview() {
    VehicleCompanionTheme {
        UpdateVehicleScreen(
            vehicle = Vehicle(id = 1),
            name = "My Awesome Car",
            onNameChange = {},
            isNameError = false,
            make = "Toyota",
            onMakeChange = {},
            isMakeError = false,
            model = "GR Yaris",
            onModelChange = {},
            isModelError = false,
            vin = "1234567890",
            onVinChange = {},
            fuelType = "Gasoline",
            onFuelTypeChange = {},
            onCloseClick = {},
            onSaveClick = {},
            onDeleteClick = {}
        )
    }
}
