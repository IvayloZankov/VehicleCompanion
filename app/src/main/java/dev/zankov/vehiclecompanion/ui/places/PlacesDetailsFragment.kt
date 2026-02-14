package dev.zankov.vehiclecompanion.ui.places

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import dev.zankov.vehiclecompanion.R
import dev.zankov.vehiclecompanion.data.remote.dto.PoiDto
import dev.zankov.vehiclecompanion.ui.rating.StarRating
import dev.zankov.vehiclecompanion.ui.theme.VehicleCompanionTheme
import androidx.core.net.toUri
import dev.zankov.vehiclecompanion.domain.model.Poi
import dev.zankov.vehiclecompanion.ui.maps.LocationMap

@Composable
fun PlacesDetailsFragment(
    viewModel: PlacesDetailsViewModel = hiltViewModel(),
) {
    val poi by viewModel.stateFlowPoi.collectAsState()
    val context = LocalContext.current

    PlacesDetailsScreen(
        poi = poi,
        onOpenInBrowserClick = { url ->
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            context.startActivity(intent)
        }
    )
}

@Composable
fun PlacesDetailsScreen(poi: Poi, onOpenInBrowserClick: (String) -> Unit) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            SubcomposeAsyncImage(
                model = poi.image,
                contentDescription = poi.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(16.dp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                error = {
                    Icon(
                        painter = painterResource(R.drawable.ic_broken_image),
                        contentDescription = stringResource(id = R.string.image_loading_failed),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = poi.name,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(text = stringResource(id = R.string.places_category, poi.category))
                StarRating(
                    rating = poi.rating,
                    starSize = 32.dp,
                    modifier = Modifier.padding(top = 8.dp),
                    starColor = MaterialTheme.colorScheme.primary
                )
                if (poi.url.isNotEmpty()) {
                    Button(
                        onClick = { onOpenInBrowserClick(poi.url) },
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                    ) {
                        Text(text = stringResource(id = R.string.open_in_browser))
                    }
                }
            }
            if (poi.location.isNotEmpty()) {
                Box {
                    LocationMap(
                        listCords = poi.location
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlacesDetailsScreenPreview() {
    val samplePoi = Poi(
        id = 1,
        name = "Sample Place",
        category = "Sample Category",
        location = listOf(1.0, 2.0),
        rating = 4.5,
        image = "https://via.placeholder.com/600/92c952",
        url = "https://www.google.com",

    )
    VehicleCompanionTheme {
        PlacesDetailsScreen(poi = samplePoi) {}
    }
}
