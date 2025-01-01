package com.example.fintrack.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.fintrack.R
import androidx.compose.material3.Icon

@Composable
fun AddActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    padding: Dp = 0.dp
) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .padding(end = padding, bottom = padding)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = "Add",
            tint = MaterialTheme.colorScheme.background
        )
    }
}
