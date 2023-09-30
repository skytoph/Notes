package com.github.skytoph.note.di

import com.github.skytoph.note.feature.note.domain.add_edit_note.interactor.AddEditNoteInteractor
import com.github.skytoph.note.feature.note.domain.usecase.NoteUseCases
import com.github.skytoph.note.feature.note.presentation.add_edit_note.UiEventMapper
import com.github.skytoph.note.feature.note.presentation.add_edit_note.interactor.BaseAddEditNoteInteractor
import com.github.skytoph.note.feature.note.presentation.add_edit_note.mapper.BaseUiEventMapper
import com.github.skytoph.note.feature.note.presentation.add_edit_note.state.BaseNoteCommunication
import com.github.skytoph.note.feature.note.presentation.add_edit_note.state.NoteCommunication
import com.github.skytoph.note.feature.note.presentation.add_edit_note.state.NoteIdCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AddEditNoteModule {

    @Provides
    @ViewModelScoped
    fun addEditNoteInteractor(useCases: NoteUseCases): AddEditNoteInteractor =
        BaseAddEditNoteInteractor(useCases)

    @Provides
    @ViewModelScoped
    fun uiEventMapper(): UiEventMapper = BaseUiEventMapper()

    @Provides
    @ViewModelScoped
    fun communication(cache: NoteIdCache): NoteCommunication.Mutable =
        BaseNoteCommunication(noteIdCache = cache)

    @Provides
    @ViewModelScoped
    fun cache(): NoteIdCache = NoteIdCache()
}