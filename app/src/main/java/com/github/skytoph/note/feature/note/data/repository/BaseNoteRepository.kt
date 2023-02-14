package com.github.skytoph.note.feature.note.data.repository

import com.github.skytoph.note.feature.note.data.datasource.NoteDao
import com.github.skytoph.note.feature.note.domain.model.Note
import com.github.skytoph.note.feature.note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class BaseNoteRepository(private val dao: NoteDao) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> =
        dao.getNotes()

    override suspend fun getNoteById(id: Int): Note? =
        dao.getNote(id)

    override suspend fun insertNote(note: Note) =
        dao.insertNote(note)

    override suspend fun deleteNote(note: Note) =
        dao.deleteNote(note)
}