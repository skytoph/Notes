package com.github.skytoph.note.feature.note.domain.notes.interactor

import com.github.skytoph.note.feature.note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesInteractor {
    fun getNotes(): Flow<List<Note>>
    suspend fun restoreNote()
    suspend fun deleteNote(note: Note)
}