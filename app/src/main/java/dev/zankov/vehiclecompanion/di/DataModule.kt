package dev.zankov.vehiclecompanion.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.zankov.vehiclecompanion.data.local.LocationsRepository
import dev.zankov.vehiclecompanion.data.local.LocationsRepositoryImpl
import dev.zankov.vehiclecompanion.data.local.VehicleRepository
import dev.zankov.vehiclecompanion.data.local.VehicleRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindVehicleRepository(
        vehicleRepositoryImpl: VehicleRepositoryImpl
    ): VehicleRepository

    @Binds
    @Singleton
    abstract fun bindLocationsRepository(
        locationsRepositoryImpl: LocationsRepositoryImpl
    ): LocationsRepository
}