package br.com.project.gassaver.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.project.gassaver.ui.components.GasSaverButton
import br.com.project.gassaver.ui.components.GasSaverDoubleTextField
import br.com.project.gassaver.ui.components.GasSaverRowRadioButtom
import br.com.project.gassaver.ui.components.GasSaverSubtitle
import br.com.project.gassaver.ui.components.GasSaverTextField
import br.com.project.gassaver.ui.screens.home.HomeUiState
import br.com.project.gassaver.ui.screens.home.HomeViewModel
import java.util.Locale

@Composable
fun VehicleRegisterContent(viewModel: HomeViewModel, state: HomeUiState, vehicleOptions: List<String>) {

    val buttonIsEnabled = (state.vehicleName.isNotEmpty() && state.vehiclePlate.isNotEmpty() && state.vehicleFuelConsumption != 0.0 && state.vehicleFuelType.isNotEmpty())

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        GasSaverSubtitle(text = "Nome do veículo:")
        GasSaverTextField(label = "Nome do veículo", value = state.vehicleName) {
            viewModel.onVehicleNameChange(it)
        }
        GasSaverSubtitle(text = "Placa do veículo:")
        GasSaverTextField(label = "Placa do veículo", value = state.vehiclePlate) {
            viewModel.onVehiclePlateChange(it.uppercase(Locale.getDefault()))
        }
        GasSaverSubtitle(text = "Tipo de combustível:")
        GasSaverRowRadioButtom(
            optins = vehicleOptions,
            onOptionSelected = viewModel::onVehicleFuelTypeSelected,
            optionSelected = state.vehicleFuelType
        )
        GasSaverSubtitle(text = "Consumo médio (Km/Litro):")
        GasSaverDoubleTextField(label = "Consumo do veículo", value = state.vehicleFuelConsumption) {
            viewModel.onVehicleFuelConsumptionChange(it)
        }
        GasSaverButton(modifier = Modifier.fillMaxWidth() ,text = "Salvar veículo", onClick = {
            viewModel.addVehicleRegister()
            viewModel.resetValues()
        }, enable = buttonIsEnabled)
        if (state.vehicleList.size > 5) {
            GasSaverSubtitle(text = "Você atingiu o limite de veículos cadastrados.")
        }
    }
}