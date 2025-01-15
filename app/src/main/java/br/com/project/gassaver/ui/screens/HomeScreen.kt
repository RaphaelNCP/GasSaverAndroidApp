package br.com.project.gassaver.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
        Text(
            text = "Home",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center),
            style = TextStyle.Default.copy(
                fontSize = 32.sp,
                color = Black
            )

        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}