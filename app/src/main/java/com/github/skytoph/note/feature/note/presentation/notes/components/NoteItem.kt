package com.github.skytoph.note.feature.note.presentation.notes.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.skytoph.note.R
import com.github.skytoph.note.feature.note.data.model.NoteEntity
import com.github.skytoph.note.ui.theme.NoteAppTheme
import com.github.skytoph.note.ui.theme.NoteColors

@Composable
fun NoteItem(
    note: NoteEntity,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    shadowOffset: Dp = 8.dp,
    onDeleteClick: () -> Unit = {}
) {
    val secondary = MaterialTheme.colors.secondary
    val surface = MaterialTheme.colors.surface

    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
                .padding(end = 8.dp, bottom = 8.dp)
                .border(
                    width = 1.dp,
                    color = secondary,
                    shape = RoundedCornerShape(cornerRadius)
                )
                .drawBehind {
                    drawRoundRect(
                        color = secondary,
                        size = size,
                        topLeft = Offset(shadowOffset.toPx(), shadowOffset.toPx()),
                        cornerRadius = CornerRadius(cornerRadius.toPx())
                    )
                }
        ) {
            val clipPath = Path().apply {
                addRoundRect(
                    RoundRect(
                        rect = Rect(offset = Offset(0f, 0f), size = size),
                        cornerRadius = CornerRadius(cornerRadius.toPx())
                    )
                )
            }
            clipPath(clipPath) {
                drawRoundRect(
                    color = surface,
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRect(
                    color = Color(note.color),
                    size = Size(width = size.width, height = 24.dp.toPx())
                )
            }
            val circleCenter = Offset(
                x = size.width - shadowOffset.toPx() - 16.dp.toPx(),
                y = 24.dp.toPx()
            )
            drawCircle(
                color = surface,
                radius = 16.dp.toPx(),
                center = circleCenter
            )
            drawCircle(
                color = Color(note.color),
                radius = 14.dp.toPx(),
                center = circleCenter
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp, top = 16.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = note.content,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = shadowOffset)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.delete_note),
                tint = surface
            )
        }
    }
}

@Composable
@Preview(showBackground = false)
fun NoteItemPreview() {
    NoteAppTheme {
        NoteItem(NoteEntity("Note title", "Content...", 1, NoteColors.list.first().toArgb()))
    }
}