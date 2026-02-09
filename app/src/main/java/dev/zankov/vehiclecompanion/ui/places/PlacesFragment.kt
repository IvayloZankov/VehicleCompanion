package dev.zankov.vehiclecompanion.ui.places

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import dev.zankov.vehiclecompanion.R
import dev.zankov.vehiclecompanion.model.Poi
import dev.zankov.vehiclecompanion.ui.alert.ErrorAlert
import dev.zankov.vehiclecompanion.ui.rating.StarRating
import dev.zankov.vehiclecompanion.ui.theme.VehicleCompanionTheme

@Composable
fun PlacesFragment(
    modifier: Modifier = Modifier,
    viewModel: PlacesViewModel = hiltViewModel(),
    onPoiClick: (id: Int) -> Unit
) {
    val statePois by viewModel.stateFlowPois.collectAsState(initial = emptyList())
    val stateError by viewModel.stateFlowError.collectAsState()

    stateError?.let {
        ErrorAlert(
            errorEvent = it,
            onDismiss = { viewModel.onErrorDismissed() }
        )
    }

    PlacesScreen(
        modifier = modifier,
        statePois = statePois,
        onPoiClick = onPoiClick
    )
}

@Composable
fun PlacesScreen(
    modifier: Modifier = Modifier,
    statePois: List<Poi>,
    onPoiClick: (id: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(statePois) { poi ->
            PoiCard(
                poi = poi,
                onClick = { onPoiClick(poi.id) }
            )
        }
    }
}

@Composable
fun PoiCard(
    poi: Poi,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                model = poi.v320x320Url,
                contentDescription = poi.name, // Important for accessibility
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentScale = ContentScale.Crop, // Fills the size, cropping if necessary
                loading = {
                    // Show a spinner while the image is loading
                    CircularProgressIndicator(
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                error = {
                    Icon(
                        painter = painterResource(R.drawable.ic_broken_image),
                        contentDescription = stringResource(id = R.string.image_loading_failed),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = poi.name,
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = poi.categoryName,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 8.dp)
                )
                StarRating(
                    rating = poi.rating,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlacesPreview() {
    VehicleCompanionTheme {
        PlacesScreen(
            statePois = listOf(
                Poi(
                    name = "Example POI with a very long name that will not fit on the screen",
                    categoryName = "Category",
                    rating = 4.5
                ),
                Poi(
                    name = "Example POI",
                    categoryName = "Category",
                    rating = 3.0
                ),
                Poi(
                    name = "Example POI",
                    categoryName = "Category",
                    rating = 5.0
                )
            ),
            onPoiClick = {}
        )
    }
}
