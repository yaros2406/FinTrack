package com.example.fintrack.ui.sections

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fintrack.R
import com.example.fintrack.ui.utils.Constants.Dimensions.AMOUNT_PADDING_END
import com.example.fintrack.ui.utils.Constants.Dimensions.ICON_SIZE
import com.example.fintrack.ui.utils.Constants.Dimensions.TRANSACTION_ITEM_SPACING
import com.example.fintrack.ui.utils.Constants.Texts.BANK_AMOUNT
import com.example.fintrack.ui.utils.Constants.Texts.BANK_SUBTITLE
import com.example.fintrack.ui.utils.Constants.Texts.BANK_TITLE
import com.example.fintrack.ui.utils.Constants.Texts.FOOD_AMOUNT
import com.example.fintrack.ui.utils.Constants.Texts.FOOD_SUBTITLE
import com.example.fintrack.ui.utils.Constants.Texts.FOOD_TITLE
import com.example.fintrack.ui.utils.Constants.Texts.OTHER_PERIODS_TOAST
import com.example.fintrack.ui.utils.Constants.Texts.TODAY_LABEL
import com.example.fintrack.ui.utils.Constants.Texts.TRANSACTIONS_HISTORY_TITLE
import com.example.fintrack.ui.utils.MaterialThemeSpacing

@Composable
fun TransactionsSection() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = MaterialThemeSpacing.spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = TRANSACTIONS_HISTORY_TITLE,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = TODAY_LABEL,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            IconButton(onClick = { Toast.makeText(context, OTHER_PERIODS_TOAST, Toast.LENGTH_SHORT).show() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_expand_more),
                    contentDescription = "Expand Menu",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
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

@Composable
fun TransactionItem(
    iconResId: Int,
    title: String,
    subtitle: String,
    amount: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = title,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(ICON_SIZE.dp)
            )
            Spacer(modifier = Modifier.width(MaterialThemeSpacing.spacing.extraSmall))

            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Text(
            text = amount,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(end = AMOUNT_PADDING_END.dp)
        )
    }
}