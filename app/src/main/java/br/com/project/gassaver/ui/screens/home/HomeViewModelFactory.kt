package br.com.project.gassaver.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.project.gassaver.domain.usecase.GetVehicleUseCase
import br.com.project.gassaver.domain.usecase.SaveRouteTakenUseCase
import br.com.project.gassaver.domain.usecase.SaveVehicleUseCase

class HomeViewModelFactory(
    private val saveFuelRecordUseCase: SaveRouteTakenUseCase,
    private val getVehicleUseCase: GetVehicleUseCase,
    private val saveVehicleUseCase: SaveVehicleUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(saveFuelRecordUseCase, getVehicleUseCase, saveVehicleUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
