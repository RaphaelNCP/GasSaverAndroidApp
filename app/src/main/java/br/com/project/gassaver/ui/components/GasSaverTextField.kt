package br.com.project.gassaver.ui.components

import android.text.InputType
import android.text.InputType.TYPE_CLASS_NUMBER
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.KeyboardType
import br.com.project.gassaver.ui.theme.Background
import br.com.project.gassaver.ui.theme.Navy
import br.com.project.gassaver.ui.theme.Teal

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
            focusedIndicatorColor = Background,
            unfocusedLabelColor = Teal,
            focusedLabelColor = Background,

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
            focusedIndicatorColor = Background,
            unfocusedLabelColor = Teal,
            focusedLabelColor = Background,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

}