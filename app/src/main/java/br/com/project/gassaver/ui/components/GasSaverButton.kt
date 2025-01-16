package br.com.project.gassaver.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.project.gassaver.ui.theme.BackgroundDark
import br.com.project.gassaver.ui.theme.Teal

@Composable
fun GasSaverButton(
    text: String,
    enable: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Teal,
            disabledContainerColor = BackgroundDark
        ),
        shape = RoundedCornerShape(6.dp),
        enabled = enable
    ) {
        GasSaverSubtitle(text)
    }
}

@Composable
fun GasSaverTextButton(
    text: String,
    enable: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Text(
        text = text,
        modifier = Modifier
            .clickable(enabled = enable, onClick = onClick)
            .then(modifier),
        color = Teal,
        textAlign = TextAlign.Center,
    )
}
