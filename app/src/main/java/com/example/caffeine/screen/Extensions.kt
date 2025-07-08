package com.example.caffeine.screen

import android.graphics.BlurMaskFilter
import android.graphics.RectF
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt

fun Modifier.ovalShadowShape(
    color: Color = Color(0x241F1F1F), blurRadius: Float = 12f, ovalHeight: Dp = 28.dp
): Modifier = this.drawBehind {
    val paint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        this.color = color.toArgb()
        maskFilter = BlurMaskFilter(
            blurRadius, BlurMaskFilter.Blur.NORMAL
        )
    }
    val ovalTop = size.height - ovalHeight.toPx() / 2
    val ovalBottom = size.height + ovalHeight.toPx() / 2
    drawContext.canvas.nativeCanvas.drawOval(
        RectF(0f, ovalTop, size.width, ovalBottom), paint
    )
}

fun Modifier.drawShadowUnderCircle(
    shadowColor: String = "#80B94B23",
    shadowRadius: Float = 40f,
    leftOffset: Float = 0f,
    topOffset: Float = 0f,
    rightOffset: Float = 0f,
    bottomOffset: Float = 0f

): Modifier = this.drawBehind {
    drawIntoCanvas { canvas ->
        val paint = Paint().asFrameworkPaint().apply {
            isAntiAlias = true
            color = shadowColor.toColorInt()
            maskFilter = BlurMaskFilter(
                shadowRadius, BlurMaskFilter.Blur.NORMAL
            )
        }
        canvas.nativeCanvas.drawOval(
            center.x - leftOffset, center.y - topOffset, center.x + rightOffset, center.y + bottomOffset, paint
        )
    }
}