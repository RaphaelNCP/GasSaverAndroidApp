package br.com.project.gassaver.ui.screens.savedVehicles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.project.gassaver.domain.usecase.DeleteVehicleUseCase
import br.com.project.gassaver.domain.usecase.GetVehicleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SavedVehiclesViewModel(
    private val getVehicleUseCase: GetVehicleUseCase,
    private val deleteVehicleUseCase: DeleteVehicleUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SavedVehiclesUiState())
    val uiState: StateFlow<SavedVehiclesUiState> = _uiState.asStateFlow()

    fun getVehicleRegister() {
        viewModelScope.launch {
            val vehicleRegister = getVehicleUseCase()
            _uiState.value = _uiState.value.copy(vehicleList = vehicleRegister)
        }
    }

    fun deleteVehicle(vehicleId: String) {
        viewModelScope.launch {
            deleteVehicleUseCase(vehicleId)
            getVehicleRegister()
        }
    }

}