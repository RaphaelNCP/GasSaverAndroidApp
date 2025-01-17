package br.com.project.gassaver.ui.screens.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import br.com.project.gassaver.data.model.VehicleRegisterModel
import br.com.project.gassaver.ui.components.GasSaverButton
import br.com.project.gassaver.ui.components.GasSaverDoubleTextField
import br.com.project.gassaver.ui.components.GasSaverRowRadioButtom
import br.com.project.gassaver.ui.components.GasSaverSubtitle
import br.com.project.gassaver.ui.components.GasSaverText
import br.com.project.gassaver.ui.components.GasSaverTextButton
import br.com.project.gassaver.ui.components.GasSaverTextField
import br.com.project.gassaver.ui.screens.home.HomeUiState
import br.com.project.gassaver.ui.screens.home.HomeViewModel
import br.com.project.gassaver.ui.theme.Background
import br.com.project.gassaver.ui.theme.Navy
import java.util.Locale

@Composable
fun RouteTakenContent(
    viewModel: HomeViewModel,
    state: HomeUiState,
    vehicleOptions: List<String>,
    hasRegisteredVehicles: List<String>,
    buttonIsEnabled: Boolean,
    vehicleList: List<VehicleRegisterModel>
) {

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        GasSaverSubtitle(text = "Nome da rota:")
        GasSaverTextField(
            label = "Nome da rota",
            value = state.routeName
        ) { value ->
            viewModel.onRouteNameChange(value)
        }
        GasSaverSubtitle(text = "Preço do litro:")
        GasSaverRowRadioButtom(
            optins = vehicleOptions,
            onOptionSelected = viewModel::onVehicleTypeSelected,
            optionSelected = state.selectedVehicleType
        )

        if (state.selectedVehicleType == "Gasolina") {
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
            haveRegisteredVehiclesContent(viewModel, state, vehicleList)
        } else {
            DontHaveRegisteredVehiclesContent(viewModel, state)
        }

        GasSaverSubtitle(text = "Km percorridos:")

        GasSaverDoubleTextField(
            label = "Quantos Km tem a viagem",
            value = state.distance
        ) { value ->
            viewModel.onDistanceChange(value)
        }

        GasSaverButton(
            text = "Calcular consumo",
            enable = buttonIsEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {

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
                            "Consumo estimado: R$ %.2f",
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
                    GasSaverButton(text = "Adicionar", modifier = Modifier.weight(1f), enable = true) {
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

@Composable
fun haveRegisteredVehiclesContent(viewModel: HomeViewModel, state: HomeUiState, vehicleList: List<VehicleRegisterModel>) {
    if (state.selectedVehicleId.isEmpty()) {
        Column {
            vehicleList.forEach { vehicle ->
                ListItem(modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .border(
                        width = 1.dp,
                        color = Background,
                        shape = RoundedCornerShape(6.dp)
                    ),
                    colors = ListItemDefaults.colors(
                        containerColor = Navy
                    ),
                    headlineContent = {
                        GasSaverSubtitle(text = vehicle.name)
                    },
                    supportingContent = {
                        GasSaverText(text = vehicle.plate)
                    },
                    trailingContent = {
                        GasSaverTextButton(text = "Selecionar") {
                            viewModel.onFuelConsumptionChange(vehicle.fuelConsumption.toDouble())
                            viewModel.onSelectedVehicleIdChange(vehicle.id)
                        }
                    })
                Spacer(modifier = Modifier.size(12.dp))
            }
        }
    } else {
        vehicleList.forEach { vehicle ->
            if (vehicle.id == state.selectedVehicleId) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    ListItem(modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .border(
                            width = 1.dp,
                            color = Background,
                            shape = RoundedCornerShape(6.dp)
                        ),
                        colors = ListItemDefaults.colors(
                            containerColor = Navy
                        ),
                        headlineContent = {
                            GasSaverSubtitle(text = vehicle.name)
                        },
                        supportingContent = {
                            GasSaverText(text = vehicle.plate)
                        },
                        trailingContent = {
                            Column {
                                GasSaverText(text = "Consumo:")
                                GasSaverText(text = vehicle.fuelConsumption)

                            }
                        }
                    )
                }
            }
        }

    }
}

@Composable
fun DontHaveRegisteredVehiclesContent(viewModel: HomeViewModel, state: HomeUiState) {
    GasSaverSubtitle(text = "Consumo medio (Km/Litro):")

    if (state.selectedVehicleType == "Gasolina") {
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
}



