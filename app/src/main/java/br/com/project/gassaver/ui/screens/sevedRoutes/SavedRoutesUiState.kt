package br.com.project.gassaver.ui.screens.sevedRoutes

import br.com.project.gassaver.data.model.RoutesTakenModel

data class SavedRoutesUiState(
    val routeTakenList: List<RoutesTakenModel> = emptyList(),
)
