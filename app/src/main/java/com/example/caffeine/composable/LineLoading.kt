package com.example.caffeine.composable

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.caffeine.ui.theme.CaffeineBlack
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun LineLoading(
    modifier: Modifier = Modifier,
    wavelength: Dp = 80.dp,
    waveHeight: Dp = 12.dp,
    color: Color = CaffeineBlack
) {
    val waveLengthPx = with(LocalDensity.current) { wavelength.toPx() }
    val waveHeightPx = with(LocalDensity.current) { waveHeight.toPx() }

    var progress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            while (progress < 1f) {
                progress += 0.05f
                delay(150)
            }
            delay(200)
            while (progress > 0f) {
                progress -= 0.05f
                delay(150)
            }
        }
    }

    Canvas(modifier = modifier) {
        val width = size.width * progress
        val centerY = size.height / 2
        val path = Path().apply {
            moveTo(0f, centerY)
            var x = 0f
            while (x <= width) {
                val y = (sin((x / waveLengthPx) * 2 * PI) * waveHeightPx).toFloat() + centerY
                lineTo(x, y)
                x += 1f
            }
        }
        drawPath(path, color = color, style = Stroke(2.dp.toPx()))
    }
}
