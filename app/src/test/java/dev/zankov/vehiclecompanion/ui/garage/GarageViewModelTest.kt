package dev.zankov.vehiclecompanion.ui.garage

import dev.zankov.vehiclecompanion.MainDispatcherRule
import dev.zankov.vehiclecompanion.data.repository.FakeVehicleRepository
import dev.zankov.vehiclecompanion.data.repository.fakeVehicles
import dev.zankov.vehiclecompanion.domain.usecase.FetchVehiclesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GarageViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var vehicleRepository: FakeVehicleRepository
    private lateinit var fetchVehiclesUseCase: FetchVehiclesUseCase
    private lateinit var viewModel: GarageViewModel

    @Before
    fun setup() {
        vehicleRepository = FakeVehicleRepository()
        fetchVehiclesUseCase = FetchVehiclesUseCase(vehicleRepository)
        viewModel = GarageViewModel(fetchVehiclesUseCase)
    }

    @Test
    fun stateFlowVehicles_initiallyContainsFakeVehicles() = runTest {
        assertEquals(fakeVehicles, viewModel.stateFlowVehicles.value)
    }
}
