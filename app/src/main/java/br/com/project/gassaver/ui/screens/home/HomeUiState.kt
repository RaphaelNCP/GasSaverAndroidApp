package br.com.project.gassaver.ui.screens.home

import br.com.project.gassaver.data.model.VehicleRegisterModel

data class HomeUiState (
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val routeName: String = "",
    val selectedVehicleType: String = "Gasolina",
    val hasRegisteredVehicle: String = "Sim",
    val fuelPrice: Double = 0.0,
    val fuelConsumption: Double = 0.0,
    val distance: Double = 0.0,
    val fuelConsumptionResult: Double = 0.0,
    val routeTakenRegisterIsOpened: Boolean = false,
    val vehicleRegisterIsOpened: Boolean = false,
    val vehicleName: String = "",
    val vehiclePlate: String = "",
    val vehicleFuelType: String = "Gasolina",
    val vehicleFuelConsumption: Double = 0.0,
    val vehicleList: List<VehicleRegisterModel> = emptyList(),
    val selectedVehicleId: String = "",


    )