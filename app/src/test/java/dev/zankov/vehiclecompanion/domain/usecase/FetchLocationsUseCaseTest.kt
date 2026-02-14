package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.repository.FakeLocationRepository
import dev.zankov.vehiclecompanion.data.repository.fakePoiList
import dev.zankov.vehiclecompanion.domain.model.Poi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class FetchLocationsUseCaseTest {

    private lateinit var locationsRepository: FakeLocationRepository
    private lateinit var fetchLocationsUseCase: FetchLocationsUseCase

    @Before
    fun setUp() {
        locationsRepository = FakeLocationRepository()
        fetchLocationsUseCase = FetchLocationsUseCase(locationsRepository)
    }

    @Test
    fun `invoke WHEN repository succeeds THEN onSuccess is called with poi list`() = runTest {
        val southWestCorner = "42.0,23.0"
        val northEastCorner = "42.1,23.1"
        val pageSize = 10
        val expectedPoiList = fakePoiList
        locationsRepository.setPois(expectedPoiList)

        var capturedPoiList: List<Poi>? = null
        var capturedException: Throwable? = null

        fetchLocationsUseCase(
            southWestCorner = southWestCorner,
            northEastCorner = northEastCorner,
            pageSize = pageSize,
            onSuccess = { list -> capturedPoiList = list },
            onFailure = { ex -> capturedException = ex }
        )

        Assert.assertEquals("onSuccess should have received the correct POI list", expectedPoiList, capturedPoiList)
        Assert.assertNull("onFailure should not have been called", capturedException)
    }

    @Test
    fun `invoke WHEN repository fails THEN onFailure is called with exception`() = runTest {
        val southWestCorner = "42.0,23.0"
        val northEastCorner = "42.1,23.1"
        val pageSize = 10
        val expectedException = IOException("Network connection failed")
        locationsRepository.setException(expectedException)

        var capturedPoiList: List<Poi>? = null
        var capturedException: Throwable? = null

        fetchLocationsUseCase(
            southWestCorner = southWestCorner,
            northEastCorner = northEastCorner,
            pageSize = pageSize,
            onSuccess = { list -> capturedPoiList = list },
            onFailure = { ex -> capturedException = ex }
        )

        Assert.assertNull("onSuccess should not have been called", capturedPoiList)
        Assert.assertEquals("onFailure should have received the correct exception", expectedException, capturedException)
    }
}