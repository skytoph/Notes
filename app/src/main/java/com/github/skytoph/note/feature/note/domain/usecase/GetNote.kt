package com.github.skytoph.note.feature.note.domain.usecase

import com.github.skytoph.note.feature.note.data.model.NoteEntity
import com.github.skytoph.note.feature.note.domain.repository.NoteRepository

class GetNote(private val repository: NoteRepository) {

    suspend operator fun invoke(id: Int): NoteEntity? =
        repository.getNoteById(id)
}