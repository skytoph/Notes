package com.github.skytoph.note.feature.note.presentation.add_edit_note.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.toArgb
import com.github.skytoph.note.feature.note.data.cache.MutableCache
import com.github.skytoph.note.feature.note.domain.model.Note
import com.github.skytoph.note.feature.note.presentation.add_edit_note.NoteTextFieldState
import com.github.skytoph.note.feature.note.presentation.add_edit_note.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class BaseNoteCommunication(
    private val noteTitle: MutableState<NoteTextFieldState> =
        mutableStateOf(NoteTextFieldState()),
    private val noteContent: MutableState<NoteTextFieldState> =
        mutableStateOf(NoteTextFieldState()),
    private val noteColor: MutableState<Int> = mutableStateOf(Note.noteColors.random().toArgb()),
    private val eventFlow: MutableSharedFlow<UiEvent> = MutableSharedFlow(),
    private val noteIdCache: NoteIdCache
) : NoteCommunication.Mutable, NoteCommunication {

    override fun showNote(note: Note) {
        showTitle(title = note.title, titleHintVisible = false)
        showContent(content = note.content, contentHintVisible = false)
        showColor(color = note.color)
        noteIdCache.cache(note.id)
    }

    override fun showTitle(title: String?, hint: String?, titleFocus: FocusState?) =
        showTitle(
            title = title,
            hint = hint,
            titleHintVisible = if (titleFocus == null) null else !titleFocus.isFocused && noteTitle.value.text.isBlank()
        )

    override fun showTitle(title: String?, hint: String?, titleHintVisible: Boolean?) {
        title?.let { noteTitle.value = noteTitle.value.copy(text = title) }
        hint?.let { noteTitle.value = noteTitle.value.copy(hint = hint) }
        titleHintVisible?.let {
            noteTitle.value = noteTitle.value.copy(isHintVisible = titleHintVisible)
        }
    }

    override fun showContent(content: String?, hint: String?, contentFocus: FocusState?) {
        val titleHintVisible: Boolean? =
            if (contentFocus == null) null
            else !contentFocus.isFocused && noteContent.value.text.isBlank()
        showContent(content, hint = hint, contentHintVisible = titleHintVisible)
    }

    override fun showContent(content: String?, hint: String?, contentHintVisible: Boolean?) {
        content?.let { noteContent.value = noteContent.value.copy(text = content) }
        hint?.let { noteContent.value = noteContent.value.copy(hint = hint) }
        contentHintVisible?.let {
            noteContent.value = noteContent.value.copy(isHintVisible = contentHintVisible)
        }
    }

    override fun showColor(color: Int?) {
        color?.let { noteColor.value = color }
    }

    override suspend fun emitToFlow(event: UiEvent) = eventFlow.emit(event)

    override fun titleState(): State<NoteTextFieldState> = noteTitle

    override fun contentState(): State<NoteTextFieldState> = noteContent

    override fun colorState(): State<Int> = noteColor

    override fun flow(): SharedFlow<UiEvent> = eventFlow

    override fun note(): Note = Note(
        title = noteTitle.value.text,
        content = noteContent.value.text,
        timestamp = System.currentTimeMillis(),
        color = noteColor.value,
        id = noteIdCache.getCached()
    )
}

class NoteIdCache : MutableCache.Nullable<Int>(null)