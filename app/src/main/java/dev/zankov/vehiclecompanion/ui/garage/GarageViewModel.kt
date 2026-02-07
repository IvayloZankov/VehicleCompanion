package dev.zankov.vehiclecompanion.ui.garage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zankov.vehiclecompanion.domain.usecase.FetchVehiclesUseCase
import dev.zankov.vehiclecompanion.model.Vehicle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GarageViewModel @Inject constructor(
    fetchVehiclesUseCase: FetchVehiclesUseCase
) : ViewModel() {

    private val _stateFlowVehicles = MutableStateFlow(emptyList<Vehicle>())
    val stateFlowVehicles = _stateFlowVehicles.asStateFlow()

    init {
        fetchVehiclesUseCase(
            viewModelScope,
            onSuccess = {
                _stateFlowVehicles.update { it }
            },
            onFailure = {
                //TODO Log some error
            }
        )
    }
}
