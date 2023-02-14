package com.github.skytoph.note.feature.note.presentation.notes

import androidx.lifecycle.ViewModel
import com.github.skytoph.note.feature.note.domain.usecase.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val noteUseCases: NoteUseCases) : ViewModel() {

}