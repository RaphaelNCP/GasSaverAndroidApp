package br.com.project.gassaver.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.project.gassaver.data.model.RoutesTakenModel
import br.com.project.gassaver.data.model.VehicleRegisterModel
import br.com.project.gassaver.domain.usecase.GetVehicleUseCase
import br.com.project.gassaver.domain.usecase.SaveRouteTakenUseCase
import br.com.project.gassaver.domain.usecase.SaveVehicleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class HomeViewModel(
    private val saveRouteTakenUseCase: SaveRouteTakenUseCase,
    private val getVehicleUseCase: GetVehicleUseCase,
    private val saveVehicleUseCase: SaveVehicleUseCase
): ViewModel() {


    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun addRouteTaken() {
        viewModelScope.launch {
            val routeTaken =
                RoutesTakenModel(
                    id = UUID.randomUUID().toString(),
                    fuelType = _uiState.value.selectedVehicle,
                    fuelPrice = _uiState.value.fuelPrice.toString(),
                    fuelConsumption = _uiState.value.fuelConsumption.toString(),
                    distance = _uiState.value.distance.toString(),
                    fuelConsumptionResult = _uiState.value.fuelConsumptionResult.toString()
                )
            saveRouteTakenUseCase(routeTaken)
        }
    }

    fun addVehicleRegister() {
        viewModelScope.launch {
            val vehicleRegister =
                VehicleRegisterModel(
                    id = UUID.randomUUID().toString(),
                    name = _uiState.value.vehicleName,
                    plate = _uiState.value.vehiclePlate,
                    fuelType = _uiState.value.vehicleFuelType,
                    fuelConsumption = _uiState.value.vehicleFuelConsumption.toString()
                )
            saveVehicleUseCase(vehicleRegister)
        }
    }

    fun getVehicleRegister() {
        viewModelScope.launch {
            val vehicleRegister = getVehicleUseCase()
            _uiState.value = _uiState.value.copy(vehicleList = vehicleRegister)
        }
    }

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

    private fun onFuelConsumptionResultChange(fuelConsumptionResult: Double) {
        _uiState.value = _uiState.value.copy(fuelConsumptionResult = fuelConsumptionResult)
    }

    fun calculateFuelConsumption() {
        val result = (_uiState.value.distance / _uiState.value.fuelConsumption) * _uiState.value.fuelPrice
        onFuelConsumptionResultChange(result)
    }

    fun routeTakenRegisterIsOpened(routeTakenRegisterIsOpened: Boolean) {
        _uiState.value = _uiState.value.copy(routeTakenRegisterIsOpened = routeTakenRegisterIsOpened)
    }

    fun vehicleRegisterIsOpened(vehicleRegisterIsOpened: Boolean) {
        _uiState.value = _uiState.value.copy(vehicleRegisterIsOpened = vehicleRegisterIsOpened)
    }

    fun onVehicleNameChange(vehicleName: String) {
        _uiState.value = _uiState.value.copy(vehicleName = vehicleName)
    }

    fun onVehiclePlateChange(vehiclePlate: String) {
        _uiState.value = _uiState.value.copy(vehiclePlate = vehiclePlate)
    }

    fun onVehicleFuelTypeSelected(vehicleFuelType: String) {
        _uiState.value = _uiState.value.copy(vehicleFuelType = vehicleFuelType)
    }

    fun onVehicleFuelConsumptionChange(vehicleFuelConsumption: Double) {
        _uiState.value = _uiState.value.copy(vehicleFuelConsumption = vehicleFuelConsumption)
    }

    fun resetValues() {
        _uiState.value = HomeUiState()
    }
}