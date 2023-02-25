package com.github.skytoph.note.di

import com.github.skytoph.note.feature.note.domain.notes.interactor.NotesInteractor
import com.github.skytoph.note.feature.note.domain.usecase.NoteUseCases
import com.github.skytoph.note.feature.note.presentation.notes.CachedJob
import com.github.skytoph.note.feature.note.presentation.notes.interactor.BaseNotesInteractor
import com.github.skytoph.note.feature.note.presentation.notes.interactor.NoteCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object NotesModule {

    @Provides
    @ViewModelScoped
    fun notesInteractor(useCases: NoteUseCases): NotesInteractor =
        BaseNotesInteractor(useCases, NoteCache())

    @Provides
    @ViewModelScoped
    fun job(): CachedJob = CachedJob.Base()
}