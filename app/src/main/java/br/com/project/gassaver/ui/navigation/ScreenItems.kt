package br.com.project.gassaver.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AddCircleOutline
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
    data object History : ScreenItem(
        topBarItem = TopAppBarItem("Histórico", emptyList()),
        bottomBarItem = BottomAppBarItem("Histórico", Icons.Default.History)
    )
}
