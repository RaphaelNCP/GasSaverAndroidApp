package br.com.project.gassaver.ui.screens.savedVehicles

import br.com.project.gassaver.data.model.VehicleRegisterModel

data class SavedVehiclesUiState(
    val vehicleList: List<VehicleRegisterModel> = emptyList(),
)
