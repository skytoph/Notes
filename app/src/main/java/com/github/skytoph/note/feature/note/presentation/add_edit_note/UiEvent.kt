package com.github.skytoph.note.feature.note.presentation.add_edit_note

import com.github.skytoph.note.feature.note.domain.add_edit_note.interactor.NoteOperationResult

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
    object SaveNote : UiEvent()
}

interface UiEventMapper {
    fun map(source: NoteOperationResult): UiEvent
}