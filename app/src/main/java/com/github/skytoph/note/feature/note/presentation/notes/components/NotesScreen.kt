package com.github.skytoph.note.feature.note.presentation.notes.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.skytoph.note.feature.note.domain.model.Note
import com.github.skytoph.note.feature.note.presentation.notes.NotesEvent
import com.github.skytoph.note.feature.note.presentation.notes.NotesState
import com.github.skytoph.note.feature.note.presentation.notes.NotesViewModel

@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    NotesList(
        navController = navController,
        state = state,
        onOrderClick = { viewModel.onEvent(NotesEvent.ToggleOrderSection) },
        onOrderChange = { viewModel.onEvent(NotesEvent.Order(it)) },
        onDeleteClick = { viewModel.deleteNote(it) },
        onUndoClick = { viewModel.restoreNote() })
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun NotesScreenPreview() {
    val notes = listOf(Note("Note title", "Content...", 1, Note.noteColors.random().toArgb()))

    NotesList(
        navController = rememberNavController(),
        state = NotesState(notes)
    )
}