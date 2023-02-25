package com.github.skytoph.note.feature.note.core

import android.content.Context

class BaseResourceProvider(private val context: Context) : Resources.Provider {
    override fun string(id: Int): String = context.getString(id)
}