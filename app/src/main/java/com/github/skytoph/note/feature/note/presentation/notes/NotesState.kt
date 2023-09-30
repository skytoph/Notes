package com.github.skytoph.note.feature.note.presentation.notes

import com.github.skytoph.note.feature.note.data.model.NoteEntity
import com.github.skytoph.note.feature.note.domain.order.NoteOrder
import com.github.skytoph.note.feature.note.domain.order.OrderType

data class NotesState(
    val notes: List<NoteEntity> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
) {

    fun sameAs(order: NoteOrder) =
        noteOrder::class == order::class && noteOrder == order.orderType
}