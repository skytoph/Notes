package com.github.skytoph.note.feature.note.presentation.add_edit_note.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.skytoph.note.feature.note.presentation.add_edit_note.NoteTextFieldState
import com.github.skytoph.note.feature.note.presentation.add_edit_note.UiEvent
import com.github.skytoph.note.ui.theme.NoteColors
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditNote(
    navController: NavController,
    title: NoteTextFieldState,
    content: NoteTextFieldState,
    colorState: Int,
    flow: SharedFlow<UiEvent>,
    onSelectColor: (Int) -> Unit = {},
    onSave: () -> Unit = {},
    onTitleChanged: (String) -> Unit = {},
    onTitleFocusChanged: (FocusState) -> Unit = {},
    onContentChanged: (String) -> Unit = {},
    onContentFocusChanged: (FocusState) -> Unit = {},
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        flow.collectLatest { event ->
            event.handle(navController, scaffoldState.snackbarHostState)
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onSave,
                backgroundColor = MaterialTheme.colors.primary,
                elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp)
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save note")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                NoteColors.list.forEach { colorIterate ->
                    val colorInt = colorIterate.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(colorIterate)
                            .border(
                                width = 3.dp,
                                color = if (colorState == colorInt) MaterialTheme.colors.onSurface else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                onSelectColor(colorInt)
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = title.text,
                hint = title.hint,
                onValueChange = onTitleChanged,
                onFocusChange = onTitleFocusChanged,
                isHintVisible = title.isHintVisible,
                isSingleLine = true,
                textStyle = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.onSurface)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = content.text,
                hint = content.hint,
                onValueChange = onContentChanged,
                onFocusChange = onContentFocusChanged,
                isHintVisible = content.isHintVisible,
                isSingleLine = false,
                textStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface),
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}