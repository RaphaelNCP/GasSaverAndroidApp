package br.com.project.gassaver.ui.screens.home

data class HomeUiState (
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val selectedVehicle: String = "Gasolina",

    val hasRegisteredVehicle: String = "Sim",
    val fuelPrice: Double = 0.0,
    val fuelConsumption: Double = 0.0,
    val distance: Double = 0.0,
    val fuelConsumptionResult: Double = 0.0
)