package br.com.project.gassaver.ui.screens.savedVehicles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.project.gassaver.domain.usecase.DeleteVehicleUseCase
import br.com.project.gassaver.domain.usecase.GetVehicleUseCase
import br.com.project.gassaver.ui.screens.home.HomeViewModel

class SavedVehiclesViewModelFactory(
    private val getVehicleUseCase: GetVehicleUseCase,
    private val deleteVehicleUseCase: DeleteVehicleUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedVehiclesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SavedVehiclesViewModel(getVehicleUseCase, deleteVehicleUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}