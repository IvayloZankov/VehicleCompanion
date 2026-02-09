package dev.zankov.vehiclecompanion

import dev.zankov.vehiclecompanion.domain.usecase.FetchLocationsUseCaseTest
import dev.zankov.vehiclecompanion.domain.usecase.FetchVehiclesUseCaseTest
import dev.zankov.vehiclecompanion.domain.usecase.GetPoiUseCaseTest
import dev.zankov.vehiclecompanion.domain.usecase.GetVehicleUseCaseTest
import dev.zankov.vehiclecompanion.domain.usecase.RemoveVehicleUseCaseTest
import dev.zankov.vehiclecompanion.domain.usecase.SaveVehicleUseCaseTest
import dev.zankov.vehiclecompanion.ui.garage.GarageViewModelTest
import dev.zankov.vehiclecompanion.ui.garage.UpdateVehicleViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(Suite::class)
@Suite.SuiteClasses(
    FetchLocationsUseCaseTest::class,
    GetPoiUseCaseTest::class,
    FetchVehiclesUseCaseTest::class,
    GetVehicleUseCaseTest::class,
    RemoveVehicleUseCaseTest::class,
    SaveVehicleUseCaseTest::class,
    GarageViewModelTest::class,
    UpdateVehicleViewModelTest::class
)
class AllUnitTestSuiteKt