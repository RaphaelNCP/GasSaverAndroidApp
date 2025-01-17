package br.com.project.gassaver.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.project.gassaver.data.repository.RoutesTakenRepository
import br.com.project.gassaver.data.repository.VehicleRegisterRepository
import br.com.project.gassaver.domain.usecase.GetVehicleUseCase
import br.com.project.gassaver.domain.usecase.SaveRouteTakenUseCase
import br.com.project.gassaver.domain.usecase.SaveVehicleUseCase
import br.com.project.gassaver.ui.navigation.ScreenItem
import br.com.project.gassaver.ui.screens.AddScreen
import br.com.project.gassaver.ui.screens.home.HomeScreen
import br.com.project.gassaver.ui.theme.AppDeTesteTheme
import br.com.project.gassaver.ui.theme.Navy

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDeTesteTheme {
                App()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(modifier: Modifier = Modifier) {
    val screens = remember {
        listOf(
            ScreenItem.Home,
            ScreenItem.History,
        )
    }

    var currentScreen by remember { mutableStateOf(screens.first()) }

    val pagerState = rememberPagerState {
        screens.size
    }

    LaunchedEffect(currentScreen) {
        pagerState.animateScrollToPage(screens.indexOf(currentScreen))
    }

    LaunchedEffect(pagerState.targetPage) {
        currentScreen = screens[pagerState.targetPage]
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(
                    text = currentScreen.topBarItem.title,
                    style = TextStyle.Default.copy(
                        fontSize = 32.sp,
                        color = Navy,
                        fontWeight = FontWeight(500),
                        letterSpacing = 3.sp
                    )
                ) },
                actions = {
                    Row(
                        modifier = Modifier.padding(end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        currentScreen.topBarItem.icons.forEach { icon ->
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                screens.forEach { screen ->
                    with(screen.bottomBarItem) {
                        NavigationBarItem(
                            icon = { Icon(imageVector = icon, contentDescription = null) },
                            label = { Text(label) },
                            selected = currentScreen == screen,
                            onClick = { currentScreen = screen },
                            colors = NavigationBarItemDefaults.colors(
                                selectedTextColor = Navy,
                                indicatorColor = Navy,
                            )
                        )
                    }
                }
            }
        }

    ) { innerPadding ->
        HorizontalPager(state = pagerState, modifier = Modifier.padding(innerPadding)) { page ->
            when (screens[page]) {
                ScreenItem.Home -> HomeScreen(
                    saveFuelRecordUseCase = SaveRouteTakenUseCase(RoutesTakenRepository()),
                    getVehicleUseCase = GetVehicleUseCase(VehicleRegisterRepository()),
                    saveVehicleUseCase = SaveVehicleUseCase(VehicleRegisterRepository())
                )
                ScreenItem.History -> AddScreen()
            }
        }
    }
}

@Preview
@Composable
private fun AppPreview() {
    AppDeTesteTheme {
        App()
    }
}