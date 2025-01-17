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

@Composable
fun VehicleRegisterContent(viewModel: HomeViewModel, state: HomeUiState, vehicleOptions: List<String>) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        GasSaverSubtitle(text = "Nome do veículo:")
        GasSaverTextField(label = "Nome do veículo", value = state.vehicleName) {
            viewModel.onVehicleNameChange(it)
        }
        GasSaverSubtitle(text = "Placa do veículo:")
        GasSaverTextField(label = "Placa do veículo", value = state.vehiclePlate) {
            viewModel.onVehiclePlateChange(it)
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
            viewModel.vehicleRegisterIsOpened(false)
        })
    }
}