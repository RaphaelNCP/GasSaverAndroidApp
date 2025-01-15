package br.com.project.gassaver.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.project.gassaver.ui.theme.Navy

@Composable
fun AddScreen(modifier: Modifier = Modifier) {
    val vehicleOptions = listOf("Gasolina", "Álcool")
    val hasRegisyteredVehicle = listOf("Sim", "Não")

    val (selectedVehicle, onVehicleSelected) = remember { mutableStateOf(vehicleOptions[0]) }
    val (hasRegisteredVehicle, onOptionSelected) = remember { mutableStateOf(hasRegisyteredVehicle[0]) }
    val (fuelPrice, onFuelPriceChange) = remember { mutableDoubleStateOf(0.0) }
    val (fuelConsumption, onFuelConsumptionChange) = remember { mutableDoubleStateOf(0.0) }
    val (distance, onDistanceChange) = remember { mutableDoubleStateOf(0.0) }
    val (fuelConsumptionResult, onFuelConsumptionResultChange) = remember { mutableDoubleStateOf(0.0) }

    fun calculateFuelConsumption(fuelPrice: Double, fuelConsumption: Double, distance: Double) {
        val result = (distance / fuelConsumption) * fuelPrice
        onFuelConsumptionResultChange(result)
    }


    Box(
        modifier
            .fillMaxSize()
            .padding(32.dp)) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.verticalScroll(rememberScrollState()).fillMaxWidth()
        ) {
            Text(
                text = "Adicionar trajeto",
                style = TextStyle.Default.copy(
                    fontSize = 28.sp,
                    color = Navy,
                    fontWeight = FontWeight(700),
                )
            )
            Text(
                text = "Preço do litro:",
                style = TextStyle.Default.copy(
                    fontSize = 20.sp,
                    color = Navy,
                    fontWeight = FontWeight(500),
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectableGroup(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                vehicleOptions.forEach { text ->
                    Row(
                        modifier = Modifier
                            .selectable(
                                selected = (text == selectedVehicle),
                                onClick = { onVehicleSelected(text) },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == selectedVehicle),
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Navy,
                                unselectedColor = Navy
                            )
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
            if (selectedVehicle == "Gasolina") {
                OutlinedTextField(
                    value = if (fuelPrice != 0.0) fuelPrice.toString() else "",
                    onValueChange = {
                        val value = it.toDoubleOrNull()
                        if (value != null) onFuelPriceChange(value)
                    },
                    label = { Text("Preço da gasolina") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Navy,
                        unfocusedTextColor = Navy,
                        focusedContainerColor = White,
                        unfocusedContainerColor = White,
                        focusedIndicatorColor = Navy,
                    )
                )
            } else {
                OutlinedTextField(
                    value = if (fuelPrice != 0.0) fuelPrice.toString() else "",
                    onValueChange = {
                        onFuelPriceChange(it.toDouble())
                    },
                    label = { Text("Preço do álcool") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Text(
                text = "Deseja usar um veículo cadastrado?",
                style = TextStyle.Default.copy(
                    fontSize = 20.sp,
                    color = Navy,
                    fontWeight = FontWeight(500),
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectableGroup(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                hasRegisyteredVehicle.forEach { text ->
                    Row(
                        modifier = Modifier
                            .selectable(
                                selected = (text == hasRegisteredVehicle),
                                onClick = { onOptionSelected(text) },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == hasRegisteredVehicle),
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Navy,
                                unselectedColor = Navy
                            )
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }


            if (hasRegisteredVehicle == "Sim") {
                Text(
                    text = "Consumo medio (Km/Litro):",
                    style = TextStyle.Default.copy(
                        fontSize = 20.sp,
                        color = Navy,
                        fontWeight = FontWeight(500),
                    )
                )

                if (selectedVehicle == "Gasolina") {
                    OutlinedTextField(
                        value = if (fuelConsumption != 0.0) fuelConsumption.toString() else "",
                        onValueChange = {
                            val value = it.toDoubleOrNull()
                            if (value != null) onFuelConsumptionChange(value)
                        },
                        label = { Text("Km/Litro da gasolina") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Navy,
                            unfocusedTextColor = Navy,
                            focusedContainerColor = White,
                            unfocusedContainerColor = White,
                            focusedIndicatorColor = Navy,
                        )
                    )
                } else {
                    OutlinedTextField(
                        value = if (fuelConsumption != 0.0) fuelConsumption.toString() else "",
                        onValueChange = {
                            onFuelConsumptionChange(it.toDouble())
                        },
                        label = { Text("Km/Litro do álcool") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            } else {
                TODO("Implementar seleção de veiculos cadastrados")
            }

            Text(
                text = "Km percorridos:",
                style = TextStyle.Default.copy(
                    fontSize = 20.sp,
                    color = Navy,
                    fontWeight = FontWeight(500),
                )
            )

            OutlinedTextField(
                value = if (distance != 0.0) distance.toString() else "",
                onValueChange = {
                    val value = it.toDoubleOrNull()
                    if (value != null) onDistanceChange(value)
                },
                label = { Text("Quantos Km tem a viagem") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { calculateFuelConsumption(fuelPrice, fuelConsumption, distance) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Navy
                ),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("Calcular consumo")
            }

            if (fuelConsumptionResult != 0.0) {
                Row(
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Navy,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Consumo estimado: $fuelConsumptionResult",
                        style = TextStyle.Default.copy(
                            fontSize = 20.sp,
                            color = Navy,
                            fontWeight = FontWeight(500),
                        )
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddScreenPreview() {
    AddScreen()
}