package com.github.skytoph.note.feature.note.domain.add_edit_note.interactor

import com.github.skytoph.note.feature.note.data.model.Note

interface AddEditNoteInteractor {
    suspend fun getNote(id: Int): Note?
    suspend fun saveNote(note: Note): NoteOperationResult
}

sealed class NoteOperationResult {
    object Success : NoteOperationResult()
    data class Error(val message: String) : NoteOperationResult()
}