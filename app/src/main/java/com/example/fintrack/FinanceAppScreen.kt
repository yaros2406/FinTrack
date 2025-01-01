package com.example.fintrack

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fintrack.ui.components.AddActionButton
import com.example.fintrack.ui.components.BottomMenu
import com.example.fintrack.ui.components.VerticalSpacer
import com.example.fintrack.ui.sections.ChartSection
import com.example.fintrack.ui.sections.RecipientsSection
import com.example.fintrack.ui.sections.TransactionsSection
import com.example.fintrack.ui.utils.Constants.Dimensions.ALPHA_DRAW_LINE
import com.example.fintrack.ui.utils.Constants.Dimensions.NOTIFY_ICON_SIZE
import com.example.fintrack.ui.utils.Constants.Dimensions.ONE
import com.example.fintrack.ui.utils.Constants.Dimensions.ROUNDED_CORNER_PERCENT
import com.example.fintrack.ui.utils.Constants.Dimensions.ZERO
import com.example.fintrack.ui.utils.Constants.Texts.ADD_ACTION
import com.example.fintrack.ui.utils.Constants.Texts.BALANCE_AMOUNT
import com.example.fintrack.ui.utils.Constants.Texts.BALANCE_LABEL
import com.example.fintrack.ui.utils.Constants.Texts.NOTIFICATION_ICON
import com.example.fintrack.ui.utils.Constants.Texts.WELCOME_MESSAGE
import com.example.fintrack.ui.utils.MaterialThemeSpacing.spacing

@Composable
fun FinanceAppScreen() {
    val context = LocalContext.current
    val padding = spacing.medium
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .offset(y = spacing.small),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Text(
                        text = WELCOME_MESSAGE,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "John!",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Box {
                    Text(
                        text = NOTIFICATION_ICON,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Box(
                        modifier = Modifier
                            .size(NOTIFY_ICON_SIZE.dp)
                            .offset(x = NOTIFY_ICON_SIZE.dp, y = ZERO.dp)
                            .background(colorScheme.error, shape = RoundedCornerShape(ROUNDED_CORNER_PERCENT))
                    )
                }
            }

            VerticalSpacer()
            HorizontalDivider(
                thickness = ONE.dp,
                color = colorScheme.secondary.copy(alpha = ALPHA_DRAW_LINE)
            )
            VerticalSpacer()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = BALANCE_AMOUNT,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorScheme.primary,
                    modifier = Modifier.alignByBaseline()
                )
                Spacer(modifier = Modifier.width(spacing.small))
                Text(
                    text = BALANCE_LABEL,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorScheme.secondary,
                    modifier = Modifier.alignByBaseline()
                )
            }

            VerticalSpacer()

            ChartSection()

            VerticalSpacer()

            RecipientsSection()

            VerticalSpacer()

            TransactionsSection()

            VerticalSpacer()
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = padding)
        ) {
            BottomMenu()
        }

        AddActionButton(
            onClick = { Toast.makeText(context, ADD_ACTION, Toast.LENGTH_SHORT).show() },
            modifier = Modifier.align(Alignment.BottomEnd),
            padding = padding
        )

    }
}