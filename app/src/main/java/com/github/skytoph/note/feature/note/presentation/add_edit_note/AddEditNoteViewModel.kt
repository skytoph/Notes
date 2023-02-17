package com.github.skytoph.note.feature.note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.note.feature.note.domain.add_edit_note.interactor.AddEditNoteInteractor
import com.github.skytoph.note.feature.note.domain.add_edit_note.interactor.NoteOperationResult
import com.github.skytoph.note.feature.note.domain.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val interactor: AddEditNoteInteractor,
    private val uiEventMapper: UiEventMapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf(NoteTextFieldState(hint = "Enter title"))
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextFieldState(hint = "Enter some content"))
    val noteContent: State<NoteTextFieldState> = _noteContent

    private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent> = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    interactor.getNote(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value =
                            noteTitle.value.copy(text = note.title, isHintVisible = false)
                        _noteContent.value =
                            noteContent.value.copy(text = note.content, isHintVisible = false)
                        _noteColor.value = note.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) = event.handle(_noteTitle, _noteContent, _noteColor)

    fun saveNote() {
        viewModelScope.launch {
            val event = interactor.saveNote(
                Note(
                    title = noteTitle.value.text,
                    content = noteContent.value.text,
                    timestamp = System.currentTimeMillis(),
                    color = noteColor.value,
                    id = currentNoteId
                )
            ).let { uiEventMapper.map(it) }
            _eventFlow.emit(event)
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }

    interface UiEventMapper {
        fun map(source: NoteOperationResult): UiEvent
    }
}