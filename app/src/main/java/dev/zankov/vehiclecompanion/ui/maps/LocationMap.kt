package dev.zankov.vehiclecompanion.ui.maps

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.GoogleMapComposable
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState

@Composable
fun LocationMap(
    listCords: List<Double>
) {
    val poiLocation = LatLng(listCords[0], listCords[1])

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            poiLocation,
            15f
        )
    }

    val markerState = rememberUpdatedMarkerState(position = poiLocation)

    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp), // Set a fixed height for the map
        cameraPositionState = cameraPositionState
    ) {
    LocationMarker(
        state = markerState,
    )
}

}

@Composable
@GoogleMapComposable
fun LocationMarker(
    state: MarkerState,
) {
    Marker(
        state = state,
    )
}
