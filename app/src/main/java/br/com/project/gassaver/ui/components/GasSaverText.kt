package br.com.project.gassaver.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.project.gassaver.ui.theme.Background
import br.com.project.gassaver.ui.theme.Navy

@Composable
fun GasSaverTitle(text: String) {
    Text(
        text = text,
        style = TextStyle.Default.copy(
            fontSize = 28.sp,
            color = Background,
            fontWeight = FontWeight(700),
        )
    )
}

@Composable
fun GasSaverSubtitle(text: String) {
    Text(
        text = text,
        style = TextStyle.Default.copy(
            fontSize = 20.sp,
            color = Background,
            fontWeight = FontWeight(500),
        )
    )
}

@Composable
fun GasSaverText(text: String) {
    Text(
        text = text,
        style = TextStyle.Default.copy(
            fontSize = 16.sp,
            color = Background,
            fontWeight = FontWeight(400),
        ),
    )
}