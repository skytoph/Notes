package com.github.skytoph.note.feature.note.presentation.notes.interactor

import com.github.skytoph.note.feature.note.data.cache.MutableCache
import com.github.skytoph.note.feature.note.domain.model.Note
import com.github.skytoph.note.feature.note.domain.notes.interactor.NotesInteractor
import com.github.skytoph.note.feature.note.domain.usecase.NoteUseCases

class BaseNotesInteractor(
    private val useCases: NoteUseCases,
    private val noteCache: MutableCache<Note?>
) : NotesInteractor {

    override fun getNotes() = useCases.getNotes()

    override suspend fun restoreNote() {
        useCases.addNote(noteCache.getCached() ?: return)
        noteCache.clear()
    }

    override suspend fun deleteNote(note: Note) {
        useCases.deleteNote(note)
        noteCache.cache(note)
    }
}

class NoteCache : MutableCache<Note?> {
    private var data: Note? = null

    override fun cache(data: Note?) {
        this.data = data
    }

    override fun getCached(): Note? = data

    override fun clear() {
        data = null
    }
}