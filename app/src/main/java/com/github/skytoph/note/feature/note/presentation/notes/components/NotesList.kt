package com.github.skytoph.note.feature.note.presentation.notes.components

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.skytoph.note.feature.note.domain.model.Note
import com.github.skytoph.note.feature.note.domain.order.NoteOrder
import com.github.skytoph.note.feature.note.presentation.notes.NotesState
import com.github.skytoph.note.feature.note.presentation.screen.Screen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotesList(
    navController: NavController,
    state: NotesState,
    onOrderClick: () -> Unit = {},
    onOrderChange: (NoteOrder) -> Unit = {},
    onDeleteClick: (Note) -> Unit = {},
    onUndoClick: () -> Unit = {},
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(route = Screen.AddEditNoteScreen.screenRoute)
                },
                backgroundColor = MaterialTheme.colors.primary,
                elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add new note")
            }
        }, scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    actionColor = MaterialTheme.colors.primary,
                    snackbarData = data
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Notes",
                    style = MaterialTheme.typography.h4
                )
                IconButton(onClick = onOrderClick) {
                    Icon(imageVector = Icons.Default.Sort, contentDescription = "Sort")
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    noteOrder = state.noteOrder,
                    onOrderChange = onOrderChange
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.notes) { note ->
                    NoteItem(note = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    route = Screen.AddEditNoteScreen(note.id.toString(), note.color)
                                        .route
                                )
                            },
                        onDeleteClick = {
                            onDeleteClick(note)
                            scope.launch {
                                val result: SnackbarResult =
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Note deleted",
                                        actionLabel = "Undo"
                                    )
                                if (result == SnackbarResult.ActionPerformed) {
                                    onUndoClick()
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun NotesScreenPreview() {
    val notes = Note.noteColors.map { color -> Note("Note title", "Content...", 1, color.toArgb()) }

    NotesList(
        navController = rememberNavController(),
        state = NotesState(notes)
    )
}