package com.github.skytoph.note.feature.note.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}