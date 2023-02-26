package com.github.skytoph.note.feature.note.presentation.notes.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.skytoph.note.feature.note.presentation.notes.NotesEvent
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