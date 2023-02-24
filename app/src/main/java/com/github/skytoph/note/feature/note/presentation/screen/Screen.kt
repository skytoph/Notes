package com.github.skytoph.note.feature.note.presentation.screen

sealed class Screen(val route: String) {

    object NotesScreen : Screen("notes_screen")

    class AddEditNoteScreen(noteId: String, noteColor: Int) :
        Screen(screenRoute + "?${noteIdArg}=${noteId}&${noteColorArg}=${noteColor}") {

        companion object {
            const val screenRoute = "add_edit_note_screen"
            const val noteIdArg = "noteId"
            const val noteColorArg = "noteColor"
            const val baseRoute = "${screenRoute}?noteId={${noteIdArg}}&noteColor={${noteColorArg}}"
        }
    }
}