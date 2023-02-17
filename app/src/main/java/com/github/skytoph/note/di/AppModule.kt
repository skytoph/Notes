package com.github.skytoph.note.di

import android.app.Application
import androidx.room.Room
import com.github.skytoph.note.feature.note.data.datasource.NoteDatabase
import com.github.skytoph.note.feature.note.data.repository.BaseNoteRepository
import com.github.skytoph.note.feature.note.domain.add_edit_note.interactor.AddEditNoteInteractor
import com.github.skytoph.note.feature.note.domain.repository.NoteRepository
import com.github.skytoph.note.feature.note.domain.usecase.*
import com.github.skytoph.note.feature.note.presentation.add_edit_note.AddEditNoteViewModel
import com.github.skytoph.note.feature.note.presentation.add_edit_note.interactor.BaseAddEditNoteInteractor
import com.github.skytoph.note.feature.note.presentation.add_edit_note.mapper.BaseUiEventMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun noteDatabase(app: Application): NoteDatabase =
        Room.databaseBuilder(app, NoteDatabase::class.java, NoteDatabase.DATABASE_NAME).build()

    @Provides
    @Singleton
    fun notesRepository(database: NoteDatabase): NoteRepository =
        BaseNoteRepository(database.noteDao)

    @Provides
    @Singleton
    fun notesUseCases(repository: NoteRepository): NoteUseCases =
        NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )

    @Provides
    @Singleton
    fun uiEventMapper(): AddEditNoteViewModel.UiEventMapper = BaseUiEventMapper()

    @Provides
    @Singleton
    fun addEditNoteInteractor(useCases: NoteUseCases): AddEditNoteInteractor =
        BaseAddEditNoteInteractor(useCases)
}