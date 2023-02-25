package com.github.skytoph.note.feature.note.core

import androidx.annotation.StringRes

interface Resources {
    interface Strings {
        fun string(@StringRes id: Int): String
    }

    interface Provider : Strings
}