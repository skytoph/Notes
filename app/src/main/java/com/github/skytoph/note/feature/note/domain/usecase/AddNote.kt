package com.github.skytoph.note.feature.note.domain.usecase

import com.github.skytoph.note.feature.note.domain.exception.InvalidNoteException
import com.github.skytoph.note.feature.note.data.model.NoteEntity
import com.github.skytoph.note.feature.note.domain.repository.NoteRepository

class AddNote(private val repository: NoteRepository) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: NoteEntity) {
        if (note.title.isBlank()) throw InvalidNoteException("The title of the note can't be empty")
        if (note.content.isBlank()) throw InvalidNoteException("The content of the note can't be empty")
        repository.insertNote(note)
    }
}