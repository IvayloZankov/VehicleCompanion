package dev.zankov.vehiclecompanion.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.zankov.vehiclecompanion.domain.LocationsRepository
import dev.zankov.vehiclecompanion.data.repository.LocationsRepositoryImpl
import dev.zankov.vehiclecompanion.domain.VehicleRepository
import dev.zankov.vehiclecompanion.data.repository.VehicleRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

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