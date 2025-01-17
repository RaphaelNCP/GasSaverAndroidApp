package br.com.project.gassaver.ui.screens.sevedRoutes

import androidx.lifecycle.ViewModelProvider
import br.com.project.gassaver.domain.usecase.DeleteRouteTakenUseCase
import br.com.project.gassaver.domain.usecase.GetRouteTakenUseCase
import br.com.project.gassaver.domain.usecase.GetVehicleUseCase

class SavedRoutesViewModelFactory(
    private val getRouteTakenUseCase: GetRouteTakenUseCase,
    private val deleteRouteTakenUseCase: DeleteRouteTakenUseCase,
): ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedRoutesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SavedRoutesViewModel(getRouteTakenUseCase, deleteRouteTakenUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}