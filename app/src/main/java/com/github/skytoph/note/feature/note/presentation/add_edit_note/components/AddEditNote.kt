package com.github.skytoph.note.feature.note.presentation.add_edit_note.components

import android.annotation.SuppressLint
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.skytoph.note.feature.note.domain.model.Note
import com.github.skytoph.note.feature.note.presentation.add_edit_note.NoteTextFieldState
import com.github.skytoph.note.feature.note.presentation.add_edit_note.UiEvent
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditNote(
    navController: NavController,
    noteColor: Int,
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

    val noteBackgroundAnimatable = remember {
        Animatable(initialValue = Color(if (noteColor != -1) noteColor else colorState))
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        flow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(message = event.message)
                }
                UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onSave,
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save note")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(noteBackgroundAnimatable.value)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Note.noteColors.forEach { colorIterate ->
                    val colorInt = colorIterate.toArgb()
                    Box(
                        modifier = androidx.compose.ui.Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(colorIterate)
                            .border(
                                width = 3.dp,
                                color = if (colorState == colorInt) Color.Black else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    noteBackgroundAnimatable.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(durationMillis = 500)
                                    )
                                }
                                onSelectColor(colorInt)
                            }
                    )
                }
            }
            Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
            TransparentHintTextField(
                text = title.text,
                hint = title.hint,
                onValueChange = onTitleChanged,
                onFocusChange = onTitleFocusChanged,
                isHintVisible = title.isHintVisible,
                isSingleLine = true,
                textStyle = MaterialTheme.typography.h5
            )
            Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
            TransparentHintTextField(
                text = content.text,
                hint = content.hint,
                onValueChange = onContentChanged,
                onFocusChange = onContentFocusChanged,
                isHintVisible = content.isHintVisible,
                isSingleLine = false,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}