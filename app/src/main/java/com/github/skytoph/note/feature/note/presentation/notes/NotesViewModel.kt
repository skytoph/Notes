package com.github.skytoph.note.feature.note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.note.feature.note.data.model.NoteEntity
import com.github.skytoph.note.feature.note.domain.notes.interactor.NotesInteractor
import com.github.skytoph.note.feature.note.domain.order.NoteOrder
import com.github.skytoph.note.feature.note.domain.order.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val interactor: NotesInteractor,
    private val getCachedJob: CachedJob
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) = event.show(_state)

    fun getNotes(noteOrder: NoteOrder) {
        interactor.getNotes(noteOrder).onEach { notes ->
            onEvent(NotesEvent.CacheNotes(noteOrder, notes))
        }.launchIn(viewModelScope).also { job -> getCachedJob.cancelAndCache(job) }
    }

    fun deleteNote(note: NoteEntity) = viewModelScope.launch { interactor.deleteNote(note) }

    fun restoreNote() = viewModelScope.launch { interactor.restoreNote() }
}