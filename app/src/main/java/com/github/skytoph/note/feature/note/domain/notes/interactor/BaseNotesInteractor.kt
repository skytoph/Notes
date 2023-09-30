package com.github.skytoph.note.feature.note.domain.notes.interactor

import com.github.skytoph.note.feature.note.data.model.NoteEntity
import com.github.skytoph.note.feature.note.domain.cache.MutableCache
import com.github.skytoph.note.feature.note.domain.order.NoteOrder
import com.github.skytoph.note.feature.note.domain.usecase.NoteUseCases

class BaseNotesInteractor(
    private val useCases: NoteUseCases,
    private val noteCache: MutableCache<NoteEntity?>
) : NotesInteractor {

    override fun getNotes(order: NoteOrder) = useCases.getNotes(order)

    override suspend fun restoreNote() {
        useCases.addNote(noteCache.getCached() ?: return)
        noteCache.clear()
    }

    override suspend fun deleteNote(note: NoteEntity) {
        useCases.deleteNote(note)
        noteCache.cache(note)
    }
}