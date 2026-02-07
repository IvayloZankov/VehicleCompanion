package dev.zankov.vehiclecompanion.ui.garage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zankov.vehiclecompanion.domain.usecase.GetVehicleUseCase
import dev.zankov.vehiclecompanion.domain.usecase.RemoveVehicleUseCase
import dev.zankov.vehiclecompanion.domain.usecase.SaveVehicleUseCase
import dev.zankov.vehiclecompanion.model.Vehicle
import dev.zankov.vehiclecompanion.navigation.AppRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateVehicleViewModel @Inject constructor(
    private val getVehicleUseCase: GetVehicleUseCase,
    private val saveVehicleUseCase: SaveVehicleUseCase,
    private val removeVehicleUseCase: RemoveVehicleUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlowSelectedVehicle = MutableStateFlow(Vehicle())
    val stateFlowSelectedVehicle = _stateFlowSelectedVehicle.asStateFlow()

    private val vehicleId: Int = savedStateHandle.get<Int>(AppRoutes.ARG_VEHICLE_ID) ?: -1

    init {
        viewModelScope.launch {
            getVehicleUseCase.invoke(
                vehicleId,
                onSuccess = { vehicle ->
                    vehicle?.let {
                        _stateFlowSelectedVehicle.update { vehicle }
                    }
                },
                onFailure = {
                    //TODO Log some error
                }
            )
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
