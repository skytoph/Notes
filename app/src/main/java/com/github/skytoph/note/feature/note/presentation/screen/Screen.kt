package com.github.skytoph.note.feature.note.presentation.screen

sealed class Screen(val route: String) {

    object NotesScreen : Screen("notes_screen")

    class AddEditNoteScreen(noteId: String) :
        Screen(screenRoute + "?${noteIdArg}=${noteId}") {

        companion object {
            const val screenRoute = "add_edit_note_screen"
            const val noteIdArg = "noteId"
            const val baseRoute = "${screenRoute}?noteId={${noteIdArg}}"
        }
    }
}