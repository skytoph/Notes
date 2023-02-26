package com.github.skytoph.note.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.github.skytoph.note.feature.note.presentation.notes.components.NotesScreenPreview

private val DarkColorPalette = darkColors(
    primary = Color.White,
    onPrimary = Color.Black,
    secondary = Color.Black,
    background = Black,
    onBackground = Color.White,
    surface = LightGray,
    onSurface = Color.White
)

private val LightColorPalette = lightColors(
    primary = BlueDark,
    onPrimary = Color.White,
    secondary = Color.Black,
    background = LightBlue,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black
)

@Composable
fun NoteAppTheme(darkTheme: Boolean = true, content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}


@Composable
@Preview(showSystemUi = true, showBackground = true)
fun ThemePreview() {
    NoteAppTheme(darkTheme = false){
        NotesScreenPreview()
    }
}