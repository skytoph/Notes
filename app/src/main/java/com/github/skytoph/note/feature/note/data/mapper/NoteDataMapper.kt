package com.github.skytoph.note.feature.note.data.mapper

import com.github.skytoph.note.feature.note.data.model.NoteEntity
import com.github.skytoph.note.feature.note.domain.model.Note

fun Note.toEntity(): NoteEntity = NoteEntity(title, content, timestamp, color, id)

fun NoteEntity.toNote(): Note = Note(title, content, timestamp, color, id)