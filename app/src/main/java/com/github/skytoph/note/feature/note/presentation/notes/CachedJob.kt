package com.github.skytoph.note.feature.note.presentation.notes

import kotlinx.coroutines.Job

interface CachedJob {
    fun cancelAndCache(job: Job)

    class Base(private var job: Job? = null) : CachedJob {

        override fun cancelAndCache(job: Job) {
            this.job?.cancel()
            this.job = job
        }
    }
}