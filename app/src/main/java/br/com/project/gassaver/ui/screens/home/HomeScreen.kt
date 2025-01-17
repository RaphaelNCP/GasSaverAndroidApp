package br.com.project.gassaver.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.project.gassaver.data.repository.RoutesTakenRepository
import br.com.project.gassaver.data.repository.VehicleRegisterRepository
import br.com.project.gassaver.domain.usecase.GetVehicleUseCase
import br.com.project.gassaver.domain.usecase.SaveRouteTakenUseCase
import br.com.project.gassaver.domain.usecase.SaveVehicleUseCase
import br.com.project.gassaver.ui.components.GasSaverCard
import br.com.project.gassaver.ui.screens.home.components.RouteTakenContent
import br.com.project.gassaver.ui.screens.home.components.VehicleRegisterContent


@Composable
fun HomeScreen(
    saveFuelRecordUseCase: SaveRouteTakenUseCase,
    saveVehicleUseCase: SaveVehicleUseCase,
    getVehicleUseCase: GetVehicleUseCase
) {

    val factory = HomeViewModelFactory(
        saveFuelRecordUseCase = saveFuelRecordUseCase,
        getVehicleUseCase = getVehicleUseCase,
        saveVehicleUseCase = saveVehicleUseCase
    )
    val viewModel: HomeViewModel = viewModel(factory = factory)

    val state by viewModel.uiState.collectAsState()

    val vehicleOptions = listOf("Gasolina", "Álcool")
    val hasRegisteredVehicles = listOf("Sim", "Não")

    val buttonIsEnabled = state.selectedVehicleType.isNotEmpty() && state.fuelPrice != 0.0 && state.distance != 0.0 && state.fuelConsumption != 0.0 && state.routeName.isNotEmpty()

    LaunchedEffect(Unit) {
        viewModel.getVehicleRegister()
    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        ) {
            GasSaverCard(
                title = "Adicionar trajeto",
                expandCard = viewModel::routeTakenRegisterIsOpened,
                isExpanded = state.routeTakenRegisterIsOpened
            ) {
                RouteTakenContent(
                    viewModel = viewModel,
                    state = state,
                    vehicleOptions = vehicleOptions,
                    hasRegisteredVehicles = hasRegisteredVehicles,
                    buttonIsEnabled = buttonIsEnabled,
                    vehicleList = state.vehicleList
                )
            }
            GasSaverCard(
                title = "Adicionar veículo",
                expandCard = viewModel::vehicleRegisterIsOpened,
                isExpanded = state.vehicleRegisterIsOpened
            ) {
                VehicleRegisterContent(
                    viewModel = viewModel,
                    state = state,
                    vehicleOptions = vehicleOptions
                )
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        saveFuelRecordUseCase = SaveRouteTakenUseCase(RoutesTakenRepository()),
        saveVehicleUseCase = SaveVehicleUseCase(VehicleRegisterRepository()),
        getVehicleUseCase = GetVehicleUseCase(VehicleRegisterRepository())
    )
}