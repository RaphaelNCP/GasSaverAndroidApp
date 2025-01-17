package br.com.project.gassaver.ui.screens.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.project.gassaver.ui.components.GasSaverButton
import br.com.project.gassaver.ui.components.GasSaverDoubleTextField
import br.com.project.gassaver.ui.components.GasSaverRowRadioButtom
import br.com.project.gassaver.ui.components.GasSaverSubtitle
import br.com.project.gassaver.ui.components.GasSaverTextButton
import br.com.project.gassaver.ui.screens.home.HomeUiState
import br.com.project.gassaver.ui.screens.home.HomeViewModel
import br.com.project.gassaver.ui.theme.Navy
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun RouteTakenContent(viewModel: HomeViewModel, state: HomeUiState, vehicleOptions: List<String>, hasRegisteredVehicles: List<String>, buttonIsEnabled: Boolean) {

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        GasSaverSubtitle(text = "Preço do litro:")
        GasSaverRowRadioButtom(
            optins = vehicleOptions,
            onOptionSelected = viewModel::onVehicleSelected,
            optionSelected = state.selectedVehicle
        )

        if (state.selectedVehicle == "Gasolina") {
            GasSaverDoubleTextField(
                label = "Preço da gasolina",
                value = state.fuelPrice
            ) { value ->
                viewModel.onFuelPriceChange(value)
            }
        } else {
            GasSaverDoubleTextField(
                label = "Preço do álcool",
                value = state.fuelPrice
            ) { value ->
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
                GasSaverDoubleTextField(
                    label = "Km/Litro de gasolina",
                    value = state.fuelConsumption
                ) { value ->
                    viewModel.onFuelConsumptionChange(value)
                }
            } else {
                GasSaverDoubleTextField(
                    label = "Km/Litro de álcool",
                    value = state.fuelConsumption
                ) { value ->
                    viewModel.onFuelConsumptionChange(value)
                }
            }
        } else {
            //TODO: Implementar a busca de veículos cadastrados
        }

        GasSaverSubtitle(text = "Km percorridos:")

        GasSaverDoubleTextField(
            label = "Quantos Km tem a viagem",
            value = state.distance
        ) { value ->
            viewModel.onDistanceChange(value)
        }

        GasSaverButton(text = "Calcular consumo", enable = buttonIsEnabled, modifier = Modifier.fillMaxWidth()) {

            viewModel.calculateFuelConsumption()

        }

        if (state.fuelConsumptionResult != 0.0) {
            Column {
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
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GasSaverButton(text = "Adicionar", modifier = Modifier.weight(1f)) {
                        viewModel.addRouteTaken()
                        viewModel.resetValues()
                        viewModel.routeTakenRegisterIsOpened(false)
                    }

                    GasSaverTextButton(text = "Limpar", modifier = Modifier.weight(1f)) {
                        viewModel.resetValues()
                        viewModel.routeTakenRegisterIsOpened(false)
                    }
                }
            }
        }
    }
}
