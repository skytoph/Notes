package com.github.skytoph.note.feature.note.domain.usecase

import com.github.skytoph.note.feature.note.domain.model.Note
import com.github.skytoph.note.feature.note.domain.repository.NoteRepository

class GetNote(private val repository: NoteRepository) {

    suspend operator fun invoke(id: Int): Note? =
        repository.getNoteById(id)
}