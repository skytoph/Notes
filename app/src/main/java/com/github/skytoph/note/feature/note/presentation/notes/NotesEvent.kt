package com.github.skytoph.note.feature.note.presentation.notes

import androidx.compose.runtime.MutableState
import com.github.skytoph.note.feature.note.data.model.Note
import com.github.skytoph.note.feature.note.domain.order.NoteOrder

sealed class NotesEvent {
    abstract fun show(state: MutableState<NotesState>)

    data class Order(val noteOrder: NoteOrder, val getNotes: (NoteOrder) -> Unit) : NotesEvent() {
        override fun show(state: MutableState<NotesState>) {
            if (state.value.sameAs(noteOrder)) return
            getNotes(noteOrder)
        }
    }

    data class CacheNotes(val noteOrder: NoteOrder, val notes: List<Note>) : NotesEvent() {
        override fun show(state: MutableState<NotesState>) {
            state.value = state.value.copy(notes = notes, noteOrder = noteOrder)
        }
    }

    object ToggleOrderSection : NotesEvent() {
        override fun show(state: MutableState<NotesState>) {
            state.value =
                state.value.copy(isOrderSectionVisible = !state.value.isOrderSectionVisible)
        }
    }
}