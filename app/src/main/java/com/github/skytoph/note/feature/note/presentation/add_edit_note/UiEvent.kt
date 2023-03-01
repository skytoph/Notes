package com.github.skytoph.note.feature.note.presentation.add_edit_note

import androidx.compose.material.SnackbarHostState
import androidx.navigation.NavController
import com.github.skytoph.note.feature.note.domain.add_edit_note.interactor.NoteOperationResult

sealed class UiEvent {
    abstract suspend fun handle(navController: NavController, hostState: SnackbarHostState)

    data class ShowSnackbar(val message: String) : UiEvent() {

        override suspend fun handle(navController: NavController, hostState: SnackbarHostState) {
            hostState.showSnackbar(message)
        }
    }

    object SaveNote : UiEvent() {

        override suspend fun handle(navController: NavController, hostState: SnackbarHostState) {
            navController.navigateUp()
        }
    }
}

interface UiEventMapper {
    fun map(source: NoteOperationResult): UiEvent
}