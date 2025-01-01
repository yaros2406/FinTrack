package com.example.fintrack.data

import androidx.compose.ui.geometry.Offset

data class ChartData(
    val points: List<Offset>,
    val highlightedPoint: Offset?,
    val highlightedValue: String?
)

fun getChartDataForPeriod(): ChartData {

    /* It's assumed that there're all the results of computations with incoming data. */

    return ChartData(
        points = listOf(
            Offset(0.18f, 0.7f),
            Offset(0.15f, 0.65f),
            Offset(0.25f, 0.8f),
            Offset(0.3f, 0.4f),
            Offset(0.32f, 0.45f),
            Offset(0.4f, 0.2f),
            Offset(0.45f, 0.42f),
            Offset(0.5f, 0.4f),
            Offset(0.55f, 0.1f),
            Offset(0.6f, 0.38f),
            Offset(0.7f, 0.0f),
            Offset(0.76f, 0.1f),
            Offset(0.76f, 0.2f),
            Offset(0.8f, 0.22f),
            Offset(0.8f, 0.3f),
            Offset(0.85f, 0.35f),
            Offset(0.88f, 0.4f),
            Offset(0.88f, 0.5f),
            Offset(0.9f, 0.5f),
        ),
        highlightedPoint = Offset(0.8f, 0.22f),
        highlightedValue = "$409"
    )
}