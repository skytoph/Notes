package com.github.skytoph.note.feature.note.presentation.add_edit_note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.note.feature.note.domain.add_edit_note.interactor.AddEditNoteInteractor
import com.github.skytoph.note.feature.note.presentation.add_edit_note.state.NoteCommunication
import com.github.skytoph.note.feature.note.presentation.screen.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val interactor: AddEditNoteInteractor,
    private val uiEventMapper: UiEventMapper,
    private val state: NoteCommunication.Mutable,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun init(titleHint: String, contentHint: String) {
        savedStateHandle.get<Int>(Screen.AddEditNoteScreen.noteIdArg)?.let { noteId ->
            if (noteId != -1) viewModelScope.launch {
                interactor.getNote(noteId)?.let { note -> state.showNote(note) }
            }
            else{
                onEvent(AddEditNoteEvent.SetTitleHint(titleHint))
                onEvent(AddEditNoteEvent.SetContentHint(contentHint))
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) = event.handle(communication = state)

    fun saveNote() = viewModelScope.launch {
        val event = interactor.saveNote(state.note()).let { uiEventMapper.map(it) }
        state.emitToFlow(event)
    }

    fun provideState(): NoteCommunication.Get = state
}