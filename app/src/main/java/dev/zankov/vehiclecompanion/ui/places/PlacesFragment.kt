package dev.zankov.vehiclecompanion.ui.places

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.zankov.vehiclecompanion.model.Poi
import dev.zankov.vehiclecompanion.ui.theme.VehicleCompanionTheme

@Composable
fun PlacesFragment(
    modifier: Modifier = Modifier,
    viewModel: PlacesViewModel = hiltViewModel()
) {

    val statePois by viewModel.stateFlowPois.collectAsState(initial = emptyList())

    PlacesScreen(
        modifier = modifier,
        statePois = statePois
    )
}

@Composable
fun PlacesScreen(
    modifier: Modifier = Modifier,
    statePois: List<Poi>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(statePois) { poi ->
            PoiCard(
                poi = poi
            )
        }
    }
}

@Composable
fun PoiCard(
    poi: Poi
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = poi.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = poi.categoryName,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = poi.rating.toString(),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
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
                    name = "Example POI",
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
            )
        )
    }
}
