package com.example.fintrack.ui.sections

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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.example.fintrack.data.getChartDataForPeriod
import com.example.fintrack.ui.utils.Constants.Dimensions.CARD_SIZE
import com.example.fintrack.ui.utils.Constants.Dimensions.CUSTOM_FONT_MEDIUM_SIZE
import com.example.fintrack.ui.utils.Constants.Dimensions.CUSTOM_FONT_SMALL_SIZE
import com.example.fintrack.ui.utils.Constants.Dimensions.LABEL_HORIZONTAL_PADDING
import com.example.fintrack.ui.utils.Constants.Dimensions.LABEL_VERTICAL_PADDING
import com.example.fintrack.ui.utils.Constants.Dimensions.RECT_CORNER_RADIUS
import com.example.fintrack.ui.utils.Constants.Dimensions.RECT_TOP_OFFSET
import com.example.fintrack.ui.utils.Constants.Dimensions.ROUNDED_CORNER
import com.example.fintrack.ui.utils.Constants.Dimensions.SELECTED_RECT_HEIGHT
import com.example.fintrack.ui.utils.Constants.Dimensions.SELECTED_RECT_WIDTH
import com.example.fintrack.ui.utils.Constants.Dimensions.STROKE_DRAW
import com.example.fintrack.ui.utils.Constants.Dimensions.STROKE_WIDTH
import com.example.fintrack.ui.utils.Constants.Texts.CHART_LABELS_HORIZONTAL
import com.example.fintrack.ui.utils.Constants.Texts.CHART_LABELS_VERTICAL
import com.example.fintrack.ui.utils.Constants.Texts.SELECTED_PERIOD
import com.example.fintrack.ui.utils.MaterialThemeSpacing.spacing

@Composable
fun ChartSection() {
    val chartData = getChartDataForPeriod()
    val colors = MaterialTheme.colorScheme

    Column {
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
                    val y = size.height - (size.height / 5 * (index + 1))
                    drawContext.canvas.nativeCanvas.drawText(
                        label,
                        LABEL_HORIZONTAL_PADDING.dp.toPx(),
                        y,
                        Paint().apply {
                            color = android.graphics.Color.WHITE
                            textSize = CUSTOM_FONT_MEDIUM_SIZE
                            textAlign = Paint.Align.LEFT
                        }
                    )
                }

                CHART_LABELS_HORIZONTAL.forEachIndexed { index, period ->
                    val x = size.width / 7 * (index + 1)
                    val textY = size.height - LABEL_VERTICAL_PADDING.dp.toPx()

                    if (period == SELECTED_PERIOD) {
                        val rectWidth = SELECTED_RECT_WIDTH.dp.toPx()
                        val rectHeight = SELECTED_RECT_HEIGHT.dp.toPx()
                        val rectLeft = x - rectWidth / 2
                        val rectTop = textY - rectHeight + RECT_TOP_OFFSET.dp.toPx() - RECT_CORNER_RADIUS.dp.toPx()

                        drawRoundRect(
                            color = colors.secondary,
                            topLeft = Offset(rectLeft, rectTop),
                            size = Size(rectWidth, rectHeight),
                            cornerRadius = CornerRadius(RECT_CORNER_RADIUS.dp.toPx())
                        )

                        drawContext.canvas.nativeCanvas.drawText(
                            SELECTED_PERIOD,
                            rectLeft + rectWidth / 2,
                            rectTop + rectHeight / 2 - (Paint().descent() + Paint().ascent()),
                            Paint().apply {
                                color = android.graphics.Color.WHITE
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
                                color = android.graphics.Color.WHITE
                                textSize = CUSTOM_FONT_MEDIUM_SIZE
                                textAlign = Paint.Align.CENTER
                            }
                        )
                    }
                }

                for (i in 0..7) {
                    val x = size.width / 7 * i
                    drawLine(
                        color = colors.background.copy(alpha = 0.3f),
                        start = Offset(x, 0f),
                        end = Offset(x, size.height),
                        strokeWidth = STROKE_WIDTH
                    )
                }

                for (i in 0..5) {
                    val y = size.height / 5 * i
                    drawLine(
                        color = colors.background.copy(alpha = 0.3f),
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = STROKE_WIDTH
                    )
                }

                if (chartData.points.isNotEmpty()) {
                    val path = Path().apply {
                        moveTo(
                            chartData.points[0].x * size.width,
                            chartData.points[0].y * size.height
                        )
                        for (i in 1 until chartData.points.size) {
                            val prev = chartData.points[i - 1]
                            val curr = chartData.points[i]
                            val midPointX = (prev.x + curr.x) / 2
                            val midPointY = (prev.y + curr.y) / 2
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
                        radius = 8.dp.toPx(),
                        center = Offset(hp.x * size.width, hp.y * size.height)
                    )

                    drawCircle(
                        color = colors.background,
                        radius = RECT_CORNER_RADIUS.dp.toPx(),
                        center = Offset(hp.x * size.width, hp.y * size.height)
                    )

                    val rectWidth = 30.dp.toPx()
                    val rectHeight = 16.dp.toPx()
                    val rectLeft = hp.x * size.width + 12.dp.toPx()
                    val rectTop = hp.y * size.height - rectHeight - 8.dp.toPx()

                    drawRoundRect(
                        color = colors.error,
                        topLeft = Offset(rectLeft, rectTop),
                        size = Size(rectWidth, rectHeight),
                        cornerRadius = CornerRadius(RECT_CORNER_RADIUS.dp.toPx())
                    )

                    drawContext.canvas.nativeCanvas.drawText(
                        "${chartData.highlightedValue}",
                        rectLeft + rectWidth / 2,
                        rectTop + rectHeight / 2 - (Paint().descent() + Paint().ascent()) / 2,
                        Paint().apply {
                            color = android.graphics.Color.WHITE
                            textSize = CUSTOM_FONT_SMALL_SIZE
                            textAlign = Paint.Align.CENTER
                        }
                    )
                }
            }
        }
    }
}
