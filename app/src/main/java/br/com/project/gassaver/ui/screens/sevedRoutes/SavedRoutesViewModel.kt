package br.com.project.gassaver.ui.screens.sevedRoutes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.project.gassaver.domain.usecase.DeleteRouteTakenUseCase
import br.com.project.gassaver.domain.usecase.GetRouteTakenUseCase
import br.com.project.gassaver.domain.usecase.GetVehicleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SavedRoutesViewModel(
    private val getRouteTakenUseCase: GetRouteTakenUseCase,
    private val deleteRouteTakenUseCase: DeleteRouteTakenUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(SavedRoutesUiState())
    val uiState: StateFlow<SavedRoutesUiState> = _uiState.asStateFlow()

    fun getRouteTaken() {
        viewModelScope.launch {
            val routeTakenList = getRouteTakenUseCase()
            _uiState.value = _uiState.value.copy(routeTakenList = routeTakenList)
        }
    }

    fun deleteRouteTaken(routeTakenId: String) {
        viewModelScope.launch {
            deleteRouteTakenUseCase(routeTakenId)
            getRouteTaken()
        }
    }
}