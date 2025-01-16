package br.com.project.gassaver.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.project.gassaver.domain.usecase.SaveRouteTakenUseCase

class HomeViewModelFactory(
    private val saveFuelRecordUseCase: SaveRouteTakenUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(saveFuelRecordUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
