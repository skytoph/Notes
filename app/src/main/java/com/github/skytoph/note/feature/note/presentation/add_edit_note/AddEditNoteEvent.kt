package com.github.skytoph.note.feature.note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState
import com.github.skytoph.note.feature.note.presentation.add_edit_note.state.NoteCommunication

sealed class AddEditNoteEvent {
    abstract fun handle(communication: NoteCommunication.ChangeNoteState)

    data class EnteredTitle(val value: String) : AddEditNoteEvent() {

        override fun handle(communication: NoteCommunication.ChangeNoteState) =
            communication.showTitle(title = value)
    }

    data class ChangeTitleFocus(val focusState: FocusState) : AddEditNoteEvent() {

        override fun handle(communication: NoteCommunication.ChangeNoteState) =
            communication.showTitle(titleFocus = focusState)
    }

    data class SetTitleHint(val value: String) : AddEditNoteEvent() {

        override fun handle(communication: NoteCommunication.ChangeNoteState) =
            communication.showTitle(hint = value)
    }

    data class EnteredContent(val value: String) : AddEditNoteEvent() {

        override fun handle(communication: NoteCommunication.ChangeNoteState) =
            communication.showContent(content = value)
    }

    data class ChangeContentFocus(val focusState: FocusState) : AddEditNoteEvent() {

        override fun handle(communication: NoteCommunication.ChangeNoteState) =
            communication.showContent(contentFocus = focusState)
    }

    data class SetContentHint(val value: String) : AddEditNoteEvent() {

        override fun handle(communication: NoteCommunication.ChangeNoteState) =
            communication.showContent(hint = value)
    }

    data class ChangeColor(val color: Int) : AddEditNoteEvent() {
        override fun handle(communication: NoteCommunication.ChangeNoteState) =
            communication.showColor(color = color)
    }
}
