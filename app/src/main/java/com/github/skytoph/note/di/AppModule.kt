package com.github.skytoph.note.di

import android.app.Application
import androidx.room.Room
import com.github.skytoph.note.feature.note.data.datasource.NoteDatabase
import com.github.skytoph.note.feature.note.data.repository.BaseNoteRepository
import com.github.skytoph.note.feature.note.domain.add_edit_note.interactor.AddEditNoteInteractor
import com.github.skytoph.note.feature.note.domain.notes.interactor.NotesInteractor
import com.github.skytoph.note.feature.note.domain.repository.NoteRepository
import com.github.skytoph.note.feature.note.domain.usecase.*
import com.github.skytoph.note.feature.note.presentation.add_edit_note.AddEditNoteViewModel
import com.github.skytoph.note.feature.note.presentation.add_edit_note.interactor.BaseAddEditNoteInteractor
import com.github.skytoph.note.feature.note.presentation.add_edit_note.mapper.BaseUiEventMapper
import com.github.skytoph.note.feature.note.presentation.notes.CachedJob
import com.github.skytoph.note.feature.note.presentation.notes.interactor.BaseNotesInteractor
import com.github.skytoph.note.feature.note.presentation.notes.interactor.NoteCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
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
}

@Module
@InstallIn(ViewModelComponent::class)
object AddEditNoteModule{

    @Provides
    @ViewModelScoped
    fun addEditNoteInteractor(useCases: NoteUseCases): AddEditNoteInteractor =
        BaseAddEditNoteInteractor(useCases)

    @Provides
    @ViewModelScoped
    fun uiEventMapper(): AddEditNoteViewModel.UiEventMapper = BaseUiEventMapper()
}

@Module
@InstallIn(ViewModelComponent::class)
object NotesModule{

    @Provides
    @ViewModelScoped
    fun notesInteractor(useCases: NoteUseCases): NotesInteractor =
        BaseNotesInteractor(useCases, NoteCache())
}