package br.com.project.gassaver.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

class BottomAppBarItem(
    val label: String,
    val icon: ImageVector,
)

class TopAppBarItem(
    val title: String,
    val icons: List<ImageVector>,
)

sealed class ScreenItem(
    val topBarItem: TopAppBarItem,
    val bottomBarItem: BottomAppBarItem
) {
    data object Home : ScreenItem(
        topBarItem = TopAppBarItem("Gas Saver", emptyList()),
        bottomBarItem = BottomAppBarItem("Home", Icons.Default.Home)
    )
    data object SavedRoutes : ScreenItem(
        topBarItem = TopAppBarItem("Rotas Salvas", emptyList()),
        bottomBarItem = BottomAppBarItem("Rotas Salvas", Icons.Default.History)
    )
    data object SavedVehicles : ScreenItem(
        topBarItem = TopAppBarItem("Veículos Salvos", emptyList()),
        bottomBarItem = BottomAppBarItem("Veículos Salvos", Icons.Default.Home)
    )
}
