package com.example.fintrack.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.fintrack.ui.utils.MaterialThemeSpacing.spacing

@Composable
fun VerticalSpacer(height: Dp = spacing.medium) {
    Spacer(modifier = Modifier.height(height))
}
