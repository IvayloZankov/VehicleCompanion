package dev.zankov.vehiclecompanion.domain.usecase

import dev.zankov.vehiclecompanion.data.local.FakeLocationRepository
import dev.zankov.vehiclecompanion.data.local.fakePoiList
import dev.zankov.vehiclecompanion.model.Poi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class GetPoiUseCaseTest {

    private lateinit var locationsRepository: FakeLocationRepository
    private lateinit var getPoiUseCase: GetPoiUseCase

    @Before
    fun setUp() {
        locationsRepository = FakeLocationRepository()
        getPoiUseCase = GetPoiUseCase(locationsRepository)
    }

    @Test
    fun `invoke WHEN poi is found THEN onSuccess is called with Poi object`() = runTest {
        val validId = 0
        val expectedPoi = fakePoiList[validId]

        var capturedPoi: Poi? = null
        var capturedException: Throwable? = null

        getPoiUseCase(
            id = validId,
            onSuccess = { poi -> capturedPoi = poi },
            onFailure = { exception -> capturedException = exception }
        )

        Assert.assertNull("onFailure should not have been called", capturedException)
        Assert.assertNotNull("The captured POI should not be null", capturedPoi)
        Assert.assertEquals("The correct POI should be returned", expectedPoi, capturedPoi)
    }

    @Test
    fun `invoke WHEN poi is NOT found THEN onSuccess is called with null`() = runTest {
        val invalidId = 999
        var capturedPoi: Poi? = Poi(id = -1, name = "Dummy POI")
        var capturedException: Throwable? = null

        getPoiUseCase(
            id = invalidId,
            onSuccess = { poi -> capturedPoi = poi },
            onFailure = { exception -> capturedException = exception }
        )

        Assert.assertNull("onFailure should not have been called", capturedException)
        Assert.assertNull("onSuccess should have been called with null", capturedPoi)
    }

    @Test
    fun `invoke WHEN repository throws exception THEN onFailure is called with exception`() = runTest {
        val expectedException = IOException("Database connection failed")
        locationsRepository.setException(expectedException) // Use the refined method

        var capturedPoi: Poi? = null
        var capturedException: Throwable? = null

        getPoiUseCase(
            id = 0,
            onSuccess = { poi -> capturedPoi = poi },
            onFailure = { exception -> capturedException = exception }
        )

        Assert.assertNull("onSuccess should not have been called", capturedPoi)
        Assert.assertNotNull("onFailure should have been called", capturedException)
        Assert.assertEquals("The correct exception should be passed to onFailure", expectedException, capturedException)
    }
}
