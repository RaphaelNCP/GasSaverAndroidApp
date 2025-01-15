package br.com.project.gassaver.ui.screens.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.project.gassaver.ui.components.GasSaverButton
import br.com.project.gassaver.ui.components.GasSaverDoubleTextField
import br.com.project.gassaver.ui.components.GasSaverRowRadioButtom
import br.com.project.gassaver.ui.components.GasSaverSubtitle
import br.com.project.gassaver.ui.components.GasSaverTitle
import br.com.project.gassaver.ui.theme.Navy
import java.util.Locale


@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(), modifier: Modifier = Modifier) {

    val state by viewModel.uiState.collectAsState()

    val vehicleOptions = listOf("Gasolina", "Álcool")
    val hasRegisteredVehicles = listOf("Sim", "Não")

    Box(
        modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 32.dp, end = 50.dp, bottom = 32.dp)) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            GasSaverTitle(text = "Adicionar trajeto")
            GasSaverSubtitle(text = "Preço do litro:")
            GasSaverRowRadioButtom(
                optins = vehicleOptions,
                onOptionSelected = viewModel::onVehicleSelected,
                optionSelected = state.selectedVehicle
            )

            if (state.selectedVehicle == "Gasolina") {
                GasSaverDoubleTextField(label = "Preço da gasolina", value = state.fuelPrice) { value ->
                    viewModel.onFuelPriceChange(value)
                }
            }
            else {
                GasSaverDoubleTextField(label = "Preço do álcool", value = state.fuelPrice) { value ->
                    viewModel.onFuelPriceChange(value)
                }
            }

            GasSaverSubtitle(text = "Deseja usar um veículo cadastrado?")
            GasSaverRowRadioButtom(
                optins = hasRegisteredVehicles,
                onOptionSelected = viewModel::onHasRegisteredVehicleSelected,
                optionSelected = state.hasRegisteredVehicle
            )

            if (state.hasRegisteredVehicle == "Sim") {
                GasSaverSubtitle(text = "Consumo medio (Km/Litro):")

                if (state.selectedVehicle == "Gasolina") {
                    GasSaverDoubleTextField(label = "Km/Litro de gasolina", value = state.fuelConsumption) { value ->
                        viewModel.onFuelConsumptionChange(value)
                    }
                } else {
                    GasSaverDoubleTextField(label = "Km/Litro de álcool", value = state.fuelConsumption) { value ->
                        viewModel.onFuelConsumptionChange(value)
                    }
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

            GasSaverDoubleTextField(label = "Quantos Km tem a viagem", value = state.distance) { value ->
                viewModel.onDistanceChange(value)
            }

            GasSaverButton(text = "Calcular consumo") {
                viewModel.calculateFuelConsumption()
            }

            if (state.fuelConsumptionResult != 0.0) {
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
                    GasSaverSubtitle(
                        text = String.format(
                            Locale("pt", "BR"),
                            "Consumo estimado: %.2f",
                            state.fuelConsumptionResult
                        )
                    )
                }
                Spacer(modifier = Modifier.size(30.dp))
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}