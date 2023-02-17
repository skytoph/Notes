package com.github.skytoph.note.feature.note.presentation.notes

import com.github.skytoph.note.feature.note.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    object ToggleOrderSection : NotesEvent()
}