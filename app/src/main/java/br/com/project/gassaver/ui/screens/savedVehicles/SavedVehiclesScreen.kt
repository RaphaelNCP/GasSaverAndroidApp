package br.com.project.gassaver.ui.screens.savedVehicles

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.project.gassaver.domain.usecase.DeleteVehicleUseCase
import br.com.project.gassaver.domain.usecase.GetVehicleUseCase
import br.com.project.gassaver.ui.components.GasSaverSubtitle
import br.com.project.gassaver.ui.theme.Background
import br.com.project.gassaver.ui.theme.Navy
import java.util.Locale

@Composable
fun SavedVehiclesScreen(
    getVehicleUseCase: GetVehicleUseCase,
    deleteVehicleUseCase: DeleteVehicleUseCase
) {
    val factory = SavedVehiclesViewModelFactory(
        getVehicleUseCase = getVehicleUseCase,
        deleteVehicleUseCase = deleteVehicleUseCase
    )
    val viewModel: SavedVehiclesViewModel = viewModel(factory = factory)

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getVehicleRegister()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        state.vehicleList.forEach { vehicle ->
            ListItem(
                modifier = Modifier
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
                    Column {
                        GasSaverSubtitle(text = vehicle.name)
                        GasSaverSubtitle(text = "Consumo: R$ ${vehicle.fuelConsumption}")
                    }
                    
                },
                supportingContent = {
                    GasSaverSubtitle(text = vehicle.plate)
                },
                trailingContent = {
                    IconButton(onClick = {viewModel.deleteVehicle(vehicle.id)}) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Background
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.size(12.dp))
        }
    }
}