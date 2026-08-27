package com.sigeschool.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun SignaturePad(
    onSignatureCaptured: (List<Offset>) -> Unit,
    modifier: Modifier = Modifier
) {
    var points by remember { mutableStateOf(listOf<Offset>()) }
    val path = remember { Path() }

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.White)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            points = points + offset
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            points = points + change.position
                        },
                        onDragEnd = {
                            onSignatureCaptured(points)
                        }
                    )
                }
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                if (points.isNotEmpty()) {
                    path.reset()
                    path.moveTo(points.first().x, points.first().y)
                    points.forEach { point ->
                        path.lineTo(point.x, point.y)
                    }
                    drawPath(
                        path = path,
                        color = Color.Black,
                        style = Stroke(width = 4f, cap = StrokeCap.Round)
                    )
                }
            }
        }
        
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { points = emptyList() }) {
                Text("Limpiar")
            }
        }
    }
}
