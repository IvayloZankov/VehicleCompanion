package dev.zankov.vehiclecompanion.ui.places

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dev.zankov.vehiclecompanion.model.Poi
import dev.zankov.vehiclecompanion.ui.theme.VehicleCompanionTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PlacesDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun verifyPlacesDetailsContent() {
        val samplePoi = Poi(
            id = 1,
            name = "Test Place",
            categoryName = "Test Category",
            rating = 4.5,
            largeImageUrl = "https://example.com/image.jpg",
            url = "https://example.com"
        )

        composeTestRule.setContent {
            VehicleCompanionTheme {
                PlacesDetailsScreen(poi = samplePoi, onOpenInBrowserClick = {})
            }
        }

        composeTestRule.onNodeWithText("Test Place").assertIsDisplayed()
        composeTestRule.onNodeWithText("Category: Test Category").assertIsDisplayed()
        composeTestRule.onNodeWithText("Open in browser").assertIsDisplayed()
    }

    @Test
    fun verifyOpenInBrowserClick() {
        var clickedUrl = ""
        val samplePoi = Poi(
            id = 1,
            name = "Test Place",
            categoryName = "Test Category",
            rating = 4.5,
            largeImageUrl = "https://example.com/image.jpg",
            url = "https://example.com/url"
        )

        composeTestRule.setContent {
            VehicleCompanionTheme {
                PlacesDetailsScreen(
                    poi = samplePoi,
                    onOpenInBrowserClick = { clickedUrl = it }
                )
            }
        }

        composeTestRule.onNodeWithText("Open in browser").performClick()
        assertEquals("https://example.com/url", clickedUrl)
    }

    @Test
    fun verifyButtonHiddenWhenUrlEmpty() {
        val samplePoi = Poi(
            id = 1,
            name = "Test Place",
            categoryName = "Test Category",
            rating = 4.5,
            largeImageUrl = "https://example.com/image.jpg",
            url = ""
        )

        composeTestRule.setContent {
            VehicleCompanionTheme {
                PlacesDetailsScreen(poi = samplePoi, onOpenInBrowserClick = {})
            }
        }

        composeTestRule.onNodeWithText("Open in browser").assertDoesNotExist()
    }
}
