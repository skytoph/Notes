package com.github.skytoph.note.feature.note.presentation.add_edit_note.components

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.skytoph.note.feature.note.domain.model.Note
import com.github.skytoph.note.feature.note.presentation.add_edit_note.AddEditNoteEvent
import com.github.skytoph.note.feature.note.presentation.add_edit_note.AddEditNoteViewModel
import com.github.skytoph.note.feature.note.presentation.add_edit_note.NoteTextFieldState
import com.github.skytoph.note.feature.note.presentation.add_edit_note.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    viewModel: AddEditNoteViewModel = hiltViewModel(),
) {
    val state = viewModel.provideState()
    val titleState = state.titleState()
    val contentState = state.contentState()
    val colorState = state.colorState()

    AddEditNote(
        navController = navController,
        noteColor = noteColor,
        title = titleState.value,
        content = contentState.value,
        colorState = colorState.value,
        flow = state.flow(),
        onSelectColor = { viewModel.onEvent(AddEditNoteEvent.ChangeColor(it)) },
        onSave = { viewModel.saveNote() },
        onTitleChanged = { viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it)) },
        onTitleFocusChanged = { viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it)) },
        onContentChanged = { viewModel.onEvent(AddEditNoteEvent.EnteredContent(it)) },
        onContentFocusChanged = { viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it)) }
    )
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun AddEditNotePreview(){
    AddEditNote(
        navController = rememberNavController(),
        noteColor = -1,
        title = NoteTextFieldState("Title","",false),
        content = NoteTextFieldState("Content of the note","",false),
        colorState = Note.noteColors.first().toArgb(),
        flow = MutableSharedFlow()
    )
}