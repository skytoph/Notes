package com.github.skytoph.note.feature.note.domain.notes.interactor

import com.github.skytoph.note.feature.note.domain.cache.MutableCache
import com.github.skytoph.note.feature.note.domain.model.Note
import com.github.skytoph.note.feature.note.domain.order.NoteOrder
import com.github.skytoph.note.feature.note.domain.usecase.NoteUseCases
import kotlinx.coroutines.flow.Flow

class BaseNotesInteractor(
    private val useCases: NoteUseCases,
    private val noteCache: MutableCache<Note?>
) : NotesInteractor {

    override fun getNotes(order: NoteOrder): Flow<List<Note>> = useCases.getNotes(order)

    override suspend fun restoreNote() {
        useCases.addNote(noteCache.getCached() ?: return)
        noteCache.clear()
    }

    override suspend fun deleteNote(note: Note) {
        useCases.deleteNote(note)
        noteCache.cache(note)
    }
}