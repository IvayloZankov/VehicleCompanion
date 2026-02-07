package dev.zankov.vehiclecompanion.ui.garage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zankov.vehiclecompanion.domain.usecase.RemoveVehicleUseCase
import dev.zankov.vehiclecompanion.domain.usecase.SaveVehicleUseCase
import dev.zankov.vehiclecompanion.model.Vehicle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateVehicleViewModel @Inject constructor(
    private val saveVehicleUseCase: SaveVehicleUseCase,
    private val removeVehicleUseCase: RemoveVehicleUseCase
) : ViewModel() {

    private val _stateFlowSelectedVehicle = MutableStateFlow(emptyList<Vehicle>())
    val stateFlowSelectedVehicle = _stateFlowSelectedVehicle.asStateFlow()

    init {
        viewModelScope.launch {

        }
    }

    fun saveVehicle(vehicle: Vehicle) {
        viewModelScope.launch {
            saveVehicleUseCase(vehicle)
        }
    }

    fun deleteVehicle(vehicle: Vehicle) {
        viewModelScope.launch {
            removeVehicleUseCase(vehicle)
        }
    }
}
