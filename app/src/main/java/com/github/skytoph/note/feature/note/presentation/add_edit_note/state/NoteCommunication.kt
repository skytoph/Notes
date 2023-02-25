package com.github.skytoph.note.feature.note.presentation.add_edit_note.state

import androidx.compose.runtime.State
import androidx.compose.ui.focus.FocusState
import com.github.skytoph.note.feature.note.domain.model.Note
import com.github.skytoph.note.feature.note.presentation.add_edit_note.NoteTextFieldState
import com.github.skytoph.note.feature.note.presentation.add_edit_note.UiEvent
import kotlinx.coroutines.flow.SharedFlow

interface NoteCommunication {

    interface ChangeNoteState {
        fun showNote(note: Note)
        fun showTitle(title: String? = null, titleFocus: FocusState? = null)
        fun showContent(content: String? = null, contentFocus: FocusState? = null)
        fun showColor(color: Int? = null)
    }

    interface SetNoteState {
        fun showTitle(title: String? = null, titleHintVisible: Boolean? = null)
        fun showContent(content: String? = null, contentHintVisible: Boolean? = null)
    }

    interface ShowNote {
        fun showNote(note: Note)
        suspend fun emitToFlow(event: UiEvent)
    }

    interface Get {
        fun titleState(): State<NoteTextFieldState>
        fun contentState(): State<NoteTextFieldState>
        fun colorState(): State<Int>
        fun flow(): SharedFlow<UiEvent>
    }

    interface MakeNote {
        fun note(): Note
    }

    interface Mutable : ChangeNoteState, ShowNote, Get, MakeNote, SetNoteState
}