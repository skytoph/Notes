package com.github.skytoph.note.feature.note.data.repository

import com.github.skytoph.note.feature.note.data.datasource.NoteDao
import com.github.skytoph.note.feature.note.data.mapper.toEntity
import com.github.skytoph.note.feature.note.data.mapper.toNote
import com.github.skytoph.note.feature.note.domain.model.Note
import com.github.skytoph.note.feature.note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BaseNoteRepository(private val dao: NoteDao) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> =
        dao.getNotes().map { list -> list.map { it.toNote() } }

    override suspend fun getNoteById(id: Int): Note? =
        dao.getNote(id)?.toNote()

    override suspend fun insertNote(note: Note) =
        dao.insertNote(note.toEntity())

    override suspend fun deleteNote(note: Note) =
        dao.deleteNote(note.toEntity())
}