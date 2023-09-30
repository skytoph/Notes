package com.github.skytoph.note.feature.note.domain.model

data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val id: Int? = null
)