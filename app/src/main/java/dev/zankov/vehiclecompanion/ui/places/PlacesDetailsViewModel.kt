package dev.zankov.vehiclecompanion.ui.places

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zankov.vehiclecompanion.domain.usecase.GetPoiUseCase
import dev.zankov.vehiclecompanion.model.Poi
import dev.zankov.vehiclecompanion.navigation.AppRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesDetailsViewModel @Inject constructor(
    private val getPoiUseCase: GetPoiUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlowPoi = MutableStateFlow(Poi())
    val stateFlowPoi = _stateFlowPoi.asStateFlow()

    private val poiId: Int = savedStateHandle.get<Int>(AppRoutes.ARG_POI_ID) ?: -1

    init {
        viewModelScope.launch {
            getPoiUseCase.invoke(
                poiId,
                onSuccess = {
                    it?.let { poi ->
                        _stateFlowPoi.update { poi }
                    }
                },
                onFailure = {
                    //TODO Log some error
                }
            )
        }
    }
}
