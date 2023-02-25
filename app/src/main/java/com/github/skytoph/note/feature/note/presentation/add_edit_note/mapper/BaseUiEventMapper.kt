package com.github.skytoph.note.feature.note.presentation.add_edit_note.mapper

import com.github.skytoph.note.feature.note.domain.add_edit_note.interactor.NoteOperationResult
import com.github.skytoph.note.feature.note.presentation.add_edit_note.UiEvent
import com.github.skytoph.note.feature.note.presentation.add_edit_note.UiEventMapper

class BaseUiEventMapper : UiEventMapper {
    override fun map(source: NoteOperationResult): UiEvent =
        when (source) {
            is NoteOperationResult.Success -> UiEvent.SaveNote
            is NoteOperationResult.Error -> UiEvent.ShowSnackbar(source.message)
        }
}