package dev.zankov.vehiclecompanion.data.repository

import dev.zankov.vehiclecompanion.domain.model.Poi

val fakePoiList = listOf(
    Poi(
        id = 1,
        name = "POI 1",
        category = "station",
        location = listOf(42.1, 23.1),
        rating = 4.1,
        url = "https://example.com/1",
        image = "https://example.com/1/v320x320.jpg"
    ),
    Poi(
        id = 2,
        name = "POI 2",
        category = "restaurant",
        location = listOf(42.2, 23.2),
        rating = 4.2,
        url = "https://example.com/2",
        image = "https://example.com/2/v320x320.jpg"
    ),
    Poi(
        id = 3,
        name = "POI 3",
        category = "park",
        location = listOf(42.3, 23.3),
        rating = 4.3,
        url = "https://example.com/3",
        image = "https://example.com/3/v320x320.jpg"
    ),
    Poi(
        id = 4,
        name = "POI 4",
        category = "station",
        location = listOf(42.4, 23.4),
        rating = 4.4,
        url = "https://example.com/4",
        image = "https://example.com/4/v320x320.jpg"
    ),
    Poi(
        id = 5,
        name = "POI 5",
        category = "museum",
        location = listOf(42.5, 23.5),
        rating = 4.5,
        url = "https://example.com/5",
        image = "https://example.com/5/v320x320.jpg"
    ),
    Poi(
        id = 6,
        name = "POI 6",
        category = "station",
        location = listOf(42.6, 23.6),
        rating = 4.6,
        url = "https://example.com/6",
        image = "https://example.com/6/v320x320.jpg"
    ),
    Poi(
        id = 7,
        name = "POI 7",
        category = "cafe",
        location = listOf(42.7, 23.7),
        rating = 4.7,
        url = "https://example.com/7",
        image = "https://example.com/7/v320x320.jpg"
    )
)
