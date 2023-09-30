package com.github.skytoph.note.feature.note.domain.notes.interactor

import com.github.skytoph.note.feature.note.data.model.NoteEntity
import com.github.skytoph.note.feature.note.domain.order.NoteOrder
import kotlinx.coroutines.flow.Flow

interface NotesInteractor {
    fun getNotes(order: NoteOrder): Flow<List<NoteEntity>>
    suspend fun restoreNote()
    suspend fun deleteNote(note: NoteEntity)
}