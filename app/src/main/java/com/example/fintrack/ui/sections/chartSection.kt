package com.example.fintrack.ui.sections

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.example.fintrack.data.getChartDataForPeriod
import com.example.fintrack.ui.utils.Constants.Dimensions.ALPHA_DRAW_LINE
import com.example.fintrack.ui.utils.Constants.Dimensions.ALPHA_SHADOW
import com.example.fintrack.ui.utils.Constants.Dimensions.CARD_SIZE
import com.example.fintrack.ui.utils.Constants.Dimensions.CUSTOM_FONT_MEDIUM_SIZE
import com.example.fintrack.ui.utils.Constants.Dimensions.CUSTOM_FONT_SMALL_SIZE
import com.example.fintrack.ui.utils.Constants.Dimensions.HORIZONTAL_CELLS
import com.example.fintrack.ui.utils.Constants.Dimensions.LABEL_HORIZONTAL_PADDING
import com.example.fintrack.ui.utils.Constants.Dimensions.LABEL_VERTICAL_PADDING
import com.example.fintrack.ui.utils.Constants.Dimensions.ONE
import com.example.fintrack.ui.utils.Constants.Dimensions.RECT_CORNER_RADIUS
import com.example.fintrack.ui.utils.Constants.Dimensions.RECT_HEIGHT
import com.example.fintrack.ui.utils.Constants.Dimensions.RECT_LEFT
import com.example.fintrack.ui.utils.Constants.Dimensions.RECT_TOP
import com.example.fintrack.ui.utils.Constants.Dimensions.RECT_TOP_OFFSET
import com.example.fintrack.ui.utils.Constants.Dimensions.RECT_WIDTH
import com.example.fintrack.ui.utils.Constants.Dimensions.ROUNDED_CORNER
import com.example.fintrack.ui.utils.Constants.Dimensions.SELECTED_RECT_HEIGHT
import com.example.fintrack.ui.utils.Constants.Dimensions.SELECTED_RECT_WIDTH
import com.example.fintrack.ui.utils.Constants.Dimensions.SHADOW_HEIGHT
import com.example.fintrack.ui.utils.Constants.Dimensions.SHADOW_RADIUS
import com.example.fintrack.ui.utils.Constants.Dimensions.START_OFFSET
import com.example.fintrack.ui.utils.Constants.Dimensions.STROKE_DRAW
import com.example.fintrack.ui.utils.Constants.Dimensions.STROKE_WIDTH
import com.example.fintrack.ui.utils.Constants.Dimensions.TWO
import com.example.fintrack.ui.utils.Constants.Dimensions.VERTICAL_CELLS
import com.example.fintrack.ui.utils.Constants.Dimensions.ZERO
import com.example.fintrack.ui.utils.Constants.Texts.CHART_LABELS_HORIZONTAL
import com.example.fintrack.ui.utils.Constants.Texts.CHART_LABELS_VERTICAL
import com.example.fintrack.ui.utils.Constants.Texts.SELECTED_PERIOD
import com.example.fintrack.ui.utils.MaterialThemeSpacing.spacing

@Composable
fun ChartSection() {
    val chartData = getChartDataForPeriod()
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(CARD_SIZE.dp)
            .drawBehind {
                val shadowColor = Color.argb(ALPHA_SHADOW, ZERO, ZERO, ZERO)
                val paint = Paint().apply {
                    color = Color.TRANSPARENT
                    setShadowLayer(SHADOW_RADIUS, START_OFFSET, SHADOW_HEIGHT, shadowColor)
                }
                drawContext.canvas.nativeCanvas.apply {
                    save()
                    translate(START_OFFSET, size.height)
                    drawRect(
                        START_OFFSET,
                        START_OFFSET,
                        size.width,
                        SHADOW_HEIGHT,
                        paint
                    )
                    restore()
                }
            }
            .background(
                color = colors.primary,
                shape = RoundedCornerShape(ROUNDED_CORNER.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .height(CARD_SIZE.dp)
                .fillMaxWidth()
                .background(
                    color = colors.primary,
                    shape = RoundedCornerShape(ROUNDED_CORNER.dp)
                )
                .padding(spacing.medium)

        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                CHART_LABELS_VERTICAL.forEachIndexed { index, label ->
                    val y = size.height - (size.height / VERTICAL_CELLS * (index + ONE))
                    drawContext.canvas.nativeCanvas.drawText(
                        label,
                        LABEL_HORIZONTAL_PADDING.dp.toPx(),
                        y,
                        Paint().apply {
                            color = Color.WHITE
                            textSize = CUSTOM_FONT_MEDIUM_SIZE
                            textAlign = Paint.Align.LEFT
                        }
                    )
                }

                CHART_LABELS_HORIZONTAL.forEachIndexed { index, period ->
                    val x = size.width / HORIZONTAL_CELLS * (index + ONE)
                    val textY = size.height - LABEL_VERTICAL_PADDING.dp.toPx()

                    if (period == SELECTED_PERIOD) {
                        val rectWidth = SELECTED_RECT_WIDTH.dp.toPx()
                        val rectHeight = SELECTED_RECT_HEIGHT.dp.toPx()
                        val rectLeft = x - rectWidth / TWO
                        val rectTop = textY - rectHeight + RECT_TOP_OFFSET.dp.toPx() - RECT_CORNER_RADIUS.dp.toPx()

                        drawRoundRect(
                            color = colors.secondary,
                            topLeft = Offset(rectLeft, rectTop),
                            size = Size(rectWidth, rectHeight),
                            cornerRadius = CornerRadius(RECT_CORNER_RADIUS.dp.toPx())
                        )

                        drawContext.canvas.nativeCanvas.drawText(
                            SELECTED_PERIOD,
                            rectLeft + rectWidth / TWO,
                            rectTop + rectHeight / TWO - (Paint().descent() + Paint().ascent()),
                            Paint().apply {
                                color = Color.WHITE
                                textSize = CUSTOM_FONT_MEDIUM_SIZE
                                textAlign = Paint.Align.CENTER
                            }
                        )

                    } else {
                        drawContext.canvas.nativeCanvas.drawText(
                            period,
                            x,
                            textY,
                            Paint().apply {
                                color = Color.WHITE
                                textSize = CUSTOM_FONT_MEDIUM_SIZE
                                textAlign = Paint.Align.CENTER
                            }
                        )
                    }
                }

                for (i in ZERO..HORIZONTAL_CELLS) {
                    val x = size.width / HORIZONTAL_CELLS * i
                    drawLine(
                        color = colors.background.copy(alpha = ALPHA_DRAW_LINE),
                        start = Offset(x, START_OFFSET),
                        end = Offset(x, size.height),
                        strokeWidth = STROKE_WIDTH
                    )
                }

                for (i in ZERO..VERTICAL_CELLS) {
                    val y = size.height / VERTICAL_CELLS * i
                    drawLine(
                        color = colors.background.copy(alpha = ALPHA_DRAW_LINE),
                        start = Offset(START_OFFSET, y),
                        end = Offset(size.width, y),
                        strokeWidth = STROKE_WIDTH
                    )
                }

                if (chartData.points.isNotEmpty()) {
                    val path = Path().apply {
                        moveTo(
                            chartData.points[ZERO].x * size.width,
                            chartData.points[ZERO].y * size.height
                        )
                        for (i in ONE until chartData.points.size) {
                            val prev = chartData.points[i - ONE]
                            val curr = chartData.points[i]
                            val midPointX = (prev.x + curr.x) / TWO
                            val midPointY = (prev.y + curr.y) / TWO
                            quadraticTo(
                                prev.x * size.width,
                                prev.y * size.height,
                                midPointX * size.width,
                                midPointY * size.height
                            )
                        }
                        lineTo(
                            chartData.points.last().x * size.width,
                            chartData.points.last().y * size.height
                        )
                    }

                    drawPath(
                        path = path,
                        color = colors.background,
                        style = Stroke(width = STROKE_DRAW)
                    )
                }

                chartData.highlightedPoint?.let { hp ->
                    drawCircle(
                        color = colors.error,
                        radius = RECT_TOP.dp.toPx(),
                        center = Offset(hp.x * size.width, hp.y * size.height)
                    )

                    drawCircle(
                        color = colors.background,
                        radius = RECT_CORNER_RADIUS.dp.toPx(),
                        center = Offset(hp.x * size.width, hp.y * size.height)
                    )

                    val rectWidth = RECT_WIDTH.dp.toPx()
                    val rectHeight = RECT_HEIGHT.dp.toPx()
                    val rectLeft = hp.x * size.width + RECT_LEFT.dp.toPx()
                    val rectTop = hp.y * size.height - rectHeight - RECT_TOP.dp.toPx()

                    drawRoundRect(
                        color = colors.error,
                        topLeft = Offset(rectLeft, rectTop),
                        size = Size(rectWidth, rectHeight),
                        cornerRadius = CornerRadius(RECT_CORNER_RADIUS.dp.toPx())
                    )

                    drawContext.canvas.nativeCanvas.drawText(
                        "${chartData.highlightedValue}",
                        rectLeft + rectWidth / TWO,
                        rectTop + rectHeight / TWO - (Paint().descent() + Paint().ascent()) / TWO,
                        Paint().apply {
                            color = Color.WHITE
                            textSize = CUSTOM_FONT_SMALL_SIZE
                            textAlign = Paint.Align.CENTER
                        }
                    )
                }
            }
        }
    }
}
