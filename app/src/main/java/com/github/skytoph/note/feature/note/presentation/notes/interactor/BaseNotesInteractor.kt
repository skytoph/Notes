package com.github.skytoph.note.feature.note.presentation.notes.interactor

import com.github.skytoph.note.feature.note.data.cache.MutableCache
import com.github.skytoph.note.feature.note.data.model.NoteEntity
import com.github.skytoph.note.feature.note.domain.notes.interactor.NotesInteractor
import com.github.skytoph.note.feature.note.domain.usecase.NoteUseCases
import com.github.skytoph.note.feature.note.domain.order.NoteOrder

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

class NoteCache : MutableCache.Nullable<NoteEntity>()