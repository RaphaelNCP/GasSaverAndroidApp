package br.com.project.gassaver.ui.screens.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.project.gassaver.data.repository.RoutesTakenRepository
import br.com.project.gassaver.domain.usecase.SaveRouteTakenUseCase
import br.com.project.gassaver.ui.components.GasSaverButton
import br.com.project.gassaver.ui.components.GasSaverDoubleTextField
import br.com.project.gassaver.ui.components.GasSaverRowRadioButtom
import br.com.project.gassaver.ui.components.GasSaverSubtitle
import br.com.project.gassaver.ui.components.GasSaverTitle
import br.com.project.gassaver.ui.screens.home.components.RouteTakenContent
import br.com.project.gassaver.ui.theme.Background
import br.com.project.gassaver.ui.theme.Navy
import br.com.project.gassaver.ui.theme.Orange
import java.util.Locale


@Composable
fun HomeScreen(saveFuelRecordUseCase: SaveRouteTakenUseCase) {

    val factory = HomeViewModelFactory(saveFuelRecordUseCase)
    val viewModel: HomeViewModel = viewModel(factory = factory)

    val state by viewModel.uiState.collectAsState()

    val vehicleOptions = listOf("Gasolina", "Álcool")
    val hasRegisteredVehicles = listOf("Sim", "Não")

    val buttonIsEnabled = state.selectedVehicle.isNotEmpty() && state.fuelPrice != 0.0 && state.distance != 0.0 && state.fuelConsumption != 0.0

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
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Navy
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                content = {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.routeTakenRegisterIsOpened(state.routeTakenRegisterIsOpened.not()) },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            GasSaverTitle(text = "Adicionar trajeto")
                            Icon(
                                imageVector = if (state.routeTakenRegisterIsOpened) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                                contentDescription = "Adicionar trajeto",
                                tint = Background
                            )
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize(
                                    animationSpec = tween(
                                        durationMillis = 500,
                                        easing = LinearOutSlowInEasing
                                    )
                                ),
                        ) {
                            if (state.routeTakenRegisterIsOpened) {
                                RouteTakenContent(
                                    viewModel = viewModel,
                                    state = state,
                                    vehicleOptions = vehicleOptions,
                                    hasRegisteredVehicles = hasRegisteredVehicles,
                                    buttonIsEnabled = buttonIsEnabled
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        saveFuelRecordUseCase = SaveRouteTakenUseCase(RoutesTakenRepository())
    )
}