package dev.zankov.vehiclecompanion.ui.garage

import androidx.lifecycle.SavedStateHandle
import dev.zankov.vehiclecompanion.MainDispatcherRule
import dev.zankov.vehiclecompanion.data.repository.FakeVehicleRepository
import dev.zankov.vehiclecompanion.data.repository.fakeVehicles
import dev.zankov.vehiclecompanion.domain.model.Vehicle
import dev.zankov.vehiclecompanion.domain.usecase.GetVehicleUseCase
import dev.zankov.vehiclecompanion.domain.usecase.RemoveVehicleUseCase
import dev.zankov.vehiclecompanion.domain.usecase.SaveVehicleUseCase
import dev.zankov.vehiclecompanion.ui.navigation.AppRoutes
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UpdateVehicleViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var vehicleRepository: FakeVehicleRepository
    private lateinit var getVehicleUseCase: GetVehicleUseCase
    private lateinit var saveVehicleUseCase: SaveVehicleUseCase
    private lateinit var removeVehicleUseCase: RemoveVehicleUseCase
    private lateinit var viewModel: UpdateVehicleViewModel

    private val testVehicle = fakeVehicles.first()

    @Before
    fun setup() {
        vehicleRepository = FakeVehicleRepository()
        getVehicleUseCase = GetVehicleUseCase(vehicleRepository)
        saveVehicleUseCase = SaveVehicleUseCase(vehicleRepository)
        removeVehicleUseCase = RemoveVehicleUseCase(vehicleRepository)
    }

    private fun createViewModel(vehicleId: Int? = null): UpdateVehicleViewModel {
        val savedStateHandle = SavedStateHandle().apply {
            if (vehicleId != null) {
                set(AppRoutes.ARG_VEHICLE_ID, vehicleId)
            }
        }
        return UpdateVehicleViewModel(
            getVehicleUseCase = getVehicleUseCase,
            saveVehicleUseCase = saveVehicleUseCase,
            removeVehicleUseCase = removeVehicleUseCase,
            savedStateHandle = savedStateHandle
        )
    }

    @Test
    fun init_loadsVehicle_whenIdExists() = runTest {
        viewModel = createViewModel(testVehicle.id)

        val loadedVehicle = viewModel.stateFlowSelectedVehicle.value
        assertEquals(testVehicle, loadedVehicle)
    }

    @Test
    fun saveVehicle_updatesRepository() = runTest {
        viewModel = createViewModel(testVehicle.id)
        val newVehicle = Vehicle(
            id = 4,
            name = "New Car",
            make = "Tesla",
            model = "Model 3",
            vin = "VIN123",
            fuelType = "Electric"
        )
        
        viewModel.saveVehicle(newVehicle)

        val repoVehicle = vehicleRepository.getVehicleStream(newVehicle.id).first()
        assertEquals(newVehicle, repoVehicle)
    }

    @Test
    fun deleteVehicle_removesFromRepository() = runTest {
        viewModel = createViewModel(testVehicle.id)
        
        viewModel.deleteVehicle(testVehicle)

        val repoVehicle = vehicleRepository.getVehicleStream(testVehicle.id).first()
        assertNull(repoVehicle)
    }
}
