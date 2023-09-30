package com.github.skytoph.note.feature.note.domain.add_edit_note.interactor

import com.github.skytoph.note.feature.note.domain.exception.InvalidNoteException
import com.github.skytoph.note.feature.note.domain.model.Note
import com.github.skytoph.note.feature.note.domain.usecase.NoteUseCases

class BaseAddEditNoteInteractor(private val noteUseCases: NoteUseCases) : AddEditNoteInteractor {
    override suspend fun getNote(id: Int): Note? = noteUseCases.getNote(id)

    override suspend fun saveNote(note: Note) = try {
        noteUseCases.addNote(note)
        NoteOperationResult.Success
    } catch (e: InvalidNoteException) {
        NoteOperationResult.Error(e.message ?: "Couldn't save note")
    }
}