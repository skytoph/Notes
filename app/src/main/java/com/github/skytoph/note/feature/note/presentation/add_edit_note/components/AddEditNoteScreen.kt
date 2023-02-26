package com.github.skytoph.note.feature.note.presentation.add_edit_note.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.skytoph.note.feature.note.presentation.add_edit_note.AddEditNoteEvent
import com.github.skytoph.note.feature.note.presentation.add_edit_note.AddEditNoteViewModel

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