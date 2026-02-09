package dev.zankov.vehiclecompanion.ui.rating

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.zankov.vehiclecompanion.R

/**
 * A Composable that displays a star rating based on a numerical value.
 *
 * @param modifier The modifier to be applied to the `Row` of stars.
 * @param rating The numerical rating value to display.
 * @param maxRating The maximum number of stars. Defaults to 5.
 * @param starSize The size of each star icon. Defaults to 16.dp.
 * @param starColor The color tint for the star icons.
 */
@Composable
fun StarRating(
    modifier: Modifier = Modifier,
    rating: Double,
    maxRating: Int = 5,
    starSize: Dp = 16.dp,
    starColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Row(modifier = modifier) {
        repeat(maxRating) { index ->
            val drawableId = if (rating >= index + 1) {
                R.drawable.ic_star_filled
            } else if (rating >= index + 0.5) {
                R.drawable.ic_star_half
            } else {
                R.drawable.ic_star_empty
            }

            Icon(
                painter = painterResource(drawableId),
                contentDescription = null,
                tint = starColor,
                modifier = Modifier.size(starSize)
            )
        }
    }
}

@Preview
@Composable
private fun StarRatingPreview() {
    StarRating(rating = 3.5)
}
