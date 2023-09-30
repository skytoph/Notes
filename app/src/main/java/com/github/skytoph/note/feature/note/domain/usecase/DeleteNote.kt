package com.github.skytoph.note.feature.note.domain.usecase

import com.github.skytoph.note.feature.note.data.model.NoteEntity
import com.github.skytoph.note.feature.note.domain.repository.NoteRepository

class DeleteNote(private val repository: NoteRepository) {

    suspend operator fun invoke(note: NoteEntity) {
        repository.deleteNote(note)
    }
}