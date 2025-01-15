package br.com.project.gassaver.ui.screens.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onVehicleSelected(selectedVehicle: String) {
        _uiState.value = _uiState.value.copy(selectedVehicle = selectedVehicle)
    }

    fun onHasRegisteredVehicleSelected(hasRegisteredVehicle: String) {
        _uiState.value = _uiState.value.copy(hasRegisteredVehicle = hasRegisteredVehicle)
    }

    fun onFuelPriceChange(fuelPrice: Double) {
        _uiState.value = _uiState.value.copy(fuelPrice = fuelPrice)
    }

    fun onFuelConsumptionChange(fuelConsumption: Double) {
        _uiState.value = _uiState.value.copy(fuelConsumption = fuelConsumption)
    }

    fun onDistanceChange(distance: Double) {
        _uiState.value = _uiState.value.copy(distance = distance)
    }

    fun onFuelConsumptionResultChange(fuelConsumptionResult: Double) {
        _uiState.value = _uiState.value.copy(fuelConsumptionResult = fuelConsumptionResult)
    }

    fun calculateFuelConsumption() {
        val result = (_uiState.value.distance / _uiState.value.fuelConsumption) * _uiState.value.fuelPrice
        onFuelConsumptionResultChange(result)
    }

    fun resetValues() {
        _uiState.value = HomeUiState()
    }
}