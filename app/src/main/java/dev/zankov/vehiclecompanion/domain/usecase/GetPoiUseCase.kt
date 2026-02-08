package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.local.LocationsRepository
import dev.zankov.vehiclecompanion.model.Poi
import dev.zankov.vehiclecompanion.model.toPoi
import javax.inject.Inject

class GetPoiUseCase @Inject constructor(
    private val repository: LocationsRepository
) {
    suspend operator fun invoke(
        id: Int,
        onSuccess: (Poi?) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        repository.getPointOfInterestById(id).collect {
            it?.let {
                onSuccess(it.toPoi())
            }
        }
    }
}
