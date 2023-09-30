package com.github.skytoph.note.feature.note.data.repository

import com.github.skytoph.note.feature.note.data.datasource.NoteDao
import com.github.skytoph.note.feature.note.data.model.NoteEntity
import com.github.skytoph.note.feature.note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class BaseNoteRepository(private val dao: NoteDao) : NoteRepository {

    override fun getNotes(): Flow<List<NoteEntity>> =
        dao.getNotes()

    override suspend fun getNoteById(id: Int): NoteEntity? =
        dao.getNote(id)

    override suspend fun insertNote(note: NoteEntity) =
        dao.insertNote(note)

    override suspend fun deleteNote(note: NoteEntity) =
        dao.deleteNote(note)
}