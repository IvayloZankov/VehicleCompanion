package dev.zankov.vehiclecompanion.ui.garage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zankov.vehiclecompanion.domain.usecase.FetchVehiclesUseCase
import dev.zankov.vehiclecompanion.domain.model.Vehicle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GarageViewModel @Inject constructor(
    private val fetchVehiclesUseCase: FetchVehiclesUseCase
) : ViewModel() {

    private val _stateFlowVehicles = MutableStateFlow(emptyList<Vehicle>())
    val stateFlowVehicles = _stateFlowVehicles.asStateFlow()

    init {
        viewModelScope.launch {
            fetchVehiclesUseCase(
                onSuccess = { newList ->
                    _stateFlowVehicles.update { newList }
                },
                onFailure = {
                    //TODO Log some error
                }
            )
        }
    }
}
