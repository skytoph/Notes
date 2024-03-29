package com.github.skytoph.note.feature.note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.skytoph.note.feature.note.presentation.add_edit_note.AddEditNoteScreen
import com.github.skytoph.note.feature.note.presentation.notes.NotesScreen
import com.github.skytoph.note.feature.note.presentation.screen.Screen
import com.github.skytoph.note.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NotesScreen.route
                    ) {
                        composable(route = Screen.NotesScreen.route) {
                            NotesScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditNoteScreen.baseRoute,
                            arguments = listOf(
                                navArgument(name = Screen.AddEditNoteScreen.noteIdArg) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                })
                        ) {
                            AddEditNoteScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}