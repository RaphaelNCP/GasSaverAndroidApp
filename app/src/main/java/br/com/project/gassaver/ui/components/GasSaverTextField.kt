package br.com.project.gassaver.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import br.com.project.gassaver.ui.theme.Navy

@Composable
fun GasSaverDoubleTextField(label: String, value: Double, onValueChange: (Double) -> Unit) {
    OutlinedTextField(
        value = if (value != 0.0) value.toString() else "",
        onValueChange = {
            val valueInformated = it.toDoubleOrNull()
            if (valueInformated != null) onValueChange(valueInformated)
        },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Navy,
            unfocusedTextColor = Navy,
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            focusedIndicatorColor = Navy,
        )
    )
}

@Composable
fun GasSaverTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            val valueInformated = it
            onValueChange(valueInformated)
        },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Navy,
            unfocusedTextColor = Navy,
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            focusedIndicatorColor = Navy,
        )
    )

}