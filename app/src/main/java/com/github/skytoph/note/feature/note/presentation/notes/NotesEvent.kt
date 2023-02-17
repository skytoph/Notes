package com.github.skytoph.note.feature.note.presentation.notes

import androidx.compose.runtime.MutableState
import com.github.skytoph.note.feature.note.domain.model.Note
import com.github.skytoph.note.feature.note.domain.util.NoteOrder

sealed class NotesEvent {
    abstract fun show(state: MutableState<NotesState>, getNotes: (NoteOrder) -> Unit)

    data class Order(val noteOrder: NoteOrder) : NotesEvent() {
        override fun show(state: MutableState<NotesState>, getNotes: (NoteOrder) -> Unit) {
            if (state.value.sameAs(noteOrder)) return
            getNotes(noteOrder)
        }
    }

    data class CacheNotes(val noteOrder: NoteOrder, val notes: List<Note>) : NotesEvent() {
        override fun show(state: MutableState<NotesState>, getNotes: (NoteOrder) -> Unit) {
            state.value = state.value.copy(notes = notes, noteOrder = noteOrder)
        }
    }

    object ToggleOrderSection : NotesEvent() {
        override fun show(state: MutableState<NotesState>, getNotes: (NoteOrder) -> Unit) {
            state.value =
                state.value.copy(isOrderSectionVisible = !state.value.isOrderSectionVisible)
        }
    }
}