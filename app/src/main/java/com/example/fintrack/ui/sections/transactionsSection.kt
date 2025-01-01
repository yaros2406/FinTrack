package com.example.fintrack.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fintrack.R
import com.example.fintrack.ui.components.PeriodDropdownMenu
import com.example.fintrack.ui.components.TransactionItem
import com.example.fintrack.ui.utils.Constants.Dimensions.TRANSACTION_ITEM_SPACING
import com.example.fintrack.ui.utils.Constants.Texts.BANK_AMOUNT
import com.example.fintrack.ui.utils.Constants.Texts.BANK_SUBTITLE
import com.example.fintrack.ui.utils.Constants.Texts.BANK_TITLE
import com.example.fintrack.ui.utils.Constants.Texts.FOOD_AMOUNT
import com.example.fintrack.ui.utils.Constants.Texts.FOOD_SUBTITLE
import com.example.fintrack.ui.utils.Constants.Texts.FOOD_TITLE
import com.example.fintrack.ui.utils.Constants.Texts.TODAY_LABEL
import com.example.fintrack.ui.utils.Constants.Texts.TRANSACTIONS_HISTORY_TITLE
import com.example.fintrack.ui.utils.MaterialThemeSpacing.spacing

@Composable
fun TransactionsSection() {
    val selectedPeriod = remember { mutableStateOf(TODAY_LABEL) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = TRANSACTIONS_HISTORY_TITLE,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )

        PeriodDropdownMenu(
            selectedPeriod = selectedPeriod.value,
            onPeriodSelected = { period -> selectedPeriod.value = period }
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(TRANSACTION_ITEM_SPACING.dp)) {
        TransactionItem(
            iconResId = R.drawable.ic_food,
            title = FOOD_TITLE,
            subtitle = FOOD_SUBTITLE,
            amount = FOOD_AMOUNT,
        )
        TransactionItem(
            iconResId = R.drawable.ic_wallet,
            title = BANK_TITLE,
            subtitle = BANK_SUBTITLE,
            amount = BANK_AMOUNT,
        )
    }
}