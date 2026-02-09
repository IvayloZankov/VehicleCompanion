package dev.zankov.vehiclecompanion.ui.places

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.zankov.vehiclecompanion.domain.usecase.FetchLocationsUseCase
import dev.zankov.vehiclecompanion.model.Poi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val fetchLocationsUseCase: FetchLocationsUseCase
) : ViewModel() {

    private val _stateFlowPois = MutableStateFlow(emptyList<Poi>())
    val stateFlowPois = _stateFlowPois.asStateFlow()

    private val _stateFlowError = MutableStateFlow<Throwable?>(null)
    val stateFlowError = _stateFlowError.asStateFlow()


    val handler = CoroutineExceptionHandler { _, exception ->
        run {
            _stateFlowError.update { exception }
        }
    }

    init {
        viewModelScope.launch(handler) {
            fetchLocationsUseCase(
                "-84.540499,39.079888",
                "-84.494260,39.113254",
                50,
                onSuccess = { newList ->
                    _stateFlowPois.update { newList }
                },
                onFailure = { throwable ->
                    _stateFlowError.update { throwable }
                }
            )
        }
    }

    fun onErrorDismissed() {
        _stateFlowError.value = null
    }
}
