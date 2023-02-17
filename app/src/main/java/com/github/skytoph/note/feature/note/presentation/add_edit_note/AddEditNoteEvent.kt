package com.github.skytoph.note.feature.note.presentation.add_edit_note

import androidx.compose.runtime.MutableState
import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {
    abstract fun handle(
        noteTitle: MutableState<NoteTextFieldState>,
        noteContent: MutableState<NoteTextFieldState>,
        noteColor: MutableState<Int>
    )

    data class EnteredTitle(val value: String) : AddEditNoteEvent() {
        override fun handle(
            noteTitle: MutableState<NoteTextFieldState>,
            noteContent: MutableState<NoteTextFieldState>,
            noteColor: MutableState<Int>
        ) {
            noteTitle.value = noteTitle.value.copy(text = value)
        }
    }

    data class ChangeTitleFocus(val focusState: FocusState) : AddEditNoteEvent() {
        override fun handle(
            noteTitle: MutableState<NoteTextFieldState>,
            noteContent: MutableState<NoteTextFieldState>,
            noteColor: MutableState<Int>
        ) {
            noteTitle.value = noteTitle.value.copy(
                isHintVisible =
                !focusState.isFocused && noteTitle.value.text.isBlank()
            )
        }
    }

    data class EnteredContent(val value: String) : AddEditNoteEvent() {
        override fun handle(
            noteTitle: MutableState<NoteTextFieldState>,
            noteContent: MutableState<NoteTextFieldState>,
            noteColor: MutableState<Int>
        ) {
            noteContent.value = noteContent.value.copy(text = value)
        }
    }

    data class ChangeContentFocus(val focusState: FocusState) : AddEditNoteEvent() {
        override fun handle(
            noteTitle: MutableState<NoteTextFieldState>,
            noteContent: MutableState<NoteTextFieldState>,
            noteColor: MutableState<Int>
        ) {
            noteContent.value = noteContent.value.copy(
                isHintVisible =
                !focusState.isFocused && noteContent.value.text.isBlank()
            )
        }
    }

    data class ChangeColor(val color: Int) : AddEditNoteEvent() {
        override fun handle(
            noteTitle: MutableState<NoteTextFieldState>,
            noteContent: MutableState<NoteTextFieldState>,
            noteColor: MutableState<Int>
        ) {
            noteColor.value = color
        }
    }
}
