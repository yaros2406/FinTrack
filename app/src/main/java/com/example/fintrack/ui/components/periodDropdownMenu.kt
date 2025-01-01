package com.example.fintrack.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.fintrack.ui.utils.Constants.Texts.LAST_MONTH_LABEL
import com.example.fintrack.ui.utils.Constants.Texts.LAST_WEEK_LABEL
import com.example.fintrack.ui.utils.Constants.Texts.SELECTED_LABEL
import com.example.fintrack.ui.utils.Constants.Texts.TODAY_LABEL
import com.example.fintrack.ui.utils.Constants.Texts.YESTERDAY_LABEL

@Composable
fun PeriodDropdownMenu(
    selectedPeriod: String,
    onPeriodSelected: (String) -> Unit
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    Row {
        TextButton(onClick = { expanded = true }) {
            Text(
                text = selectedPeriod,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Expand Menu",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listOf(TODAY_LABEL, YESTERDAY_LABEL, LAST_WEEK_LABEL, LAST_MONTH_LABEL).forEach { period ->
                DropdownMenuItem(
                    text = { Text(period) },
                    onClick = {
                        onPeriodSelected(period)
                        expanded = false
                        Toast.makeText(context, "$SELECTED_LABEL $period", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}