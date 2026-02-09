package dev.zankov.vehiclecompanion.ui.alert

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.zankov.vehiclecompanion.R

@Composable
fun ErrorAlert(
    errorEvent: Throwable,
    onDismiss: () -> Unit
) {
    stringResource(id = R.string.unknown_error)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(id = R.string.error)) },
        text = { Text(stringResource(id = R.string.unknown_error)) },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.ok))
            }
        }
    )
}
