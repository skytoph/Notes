package com.github.skytoph.note.feature.note.presentation.screen

sealed class Screen(val route: String) {
    object NotesScreen : Screen("notes_screen")
    object AddEditNoteScreen : Screen("add_edit_note_screen")
}