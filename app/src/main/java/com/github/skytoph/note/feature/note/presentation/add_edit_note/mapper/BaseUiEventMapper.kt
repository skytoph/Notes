package com.github.skytoph.note.feature.note.presentation.add_edit_note.mapper

import com.github.skytoph.note.feature.note.domain.add_edit_note.interactor.NoteOperationResult
import com.github.skytoph.note.feature.note.presentation.add_edit_note.AddEditNoteViewModel

class BaseUiEventMapper : AddEditNoteViewModel.UiEventMapper {
    override fun map(source: NoteOperationResult): AddEditNoteViewModel.UiEvent =
        when (source) {
            is NoteOperationResult.Success -> AddEditNoteViewModel.UiEvent.SaveNote
            is NoteOperationResult.Error -> AddEditNoteViewModel.UiEvent.ShowSnackbar(source.message)
        }
}