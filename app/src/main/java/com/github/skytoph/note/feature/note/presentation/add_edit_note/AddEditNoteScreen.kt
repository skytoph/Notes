package com.github.skytoph.note.feature.note.presentation.add_edit_note

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.skytoph.note.R
import com.github.skytoph.note.feature.note.domain.model.Note
import com.github.skytoph.note.feature.note.presentation.add_edit_note.components.AddEditNote
import com.github.skytoph.note.ui.theme.NoteAppTheme
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    viewModel: AddEditNoteViewModel = hiltViewModel(),
) {
    val titleHint = stringResource(id = R.string.edit_title)
    val contentHint = stringResource(id = R.string.edit_content)
    LaunchedEffect(Unit) { viewModel.init(titleHint, contentHint) }

    val state = viewModel.provideState()
    val titleState = state.titleState()
    val contentState = state.contentState()
    val colorState = state.colorState()

    AddEditNote(
        navController = navController,
        title = titleState.value,
        content = contentState.value,
        colorState = colorState.value,
        flow = state.flow(),
        onSelectColor = { viewModel.onEvent(AddEditNoteEvent.ChangeColor(it)) },
        onSave = { viewModel.saveNote() },
        onTitleChanged = { viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it)) },
        onTitleFocusChanged = { viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it)) },
        onContentChanged = { viewModel.onEvent(AddEditNoteEvent.EnteredContent(it)) }
    ) { viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it)) }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun AddEditNotePreview() {
    NoteAppTheme(darkTheme = true) {
        AddEditNote(
            navController = rememberNavController(),
            title = NoteTextFieldState("Title", "", false),
            content = NoteTextFieldState("Content of the note", "", false),
            colorState = Note.noteColors.first().toArgb(),
            flow = MutableSharedFlow()
        )
    }
}