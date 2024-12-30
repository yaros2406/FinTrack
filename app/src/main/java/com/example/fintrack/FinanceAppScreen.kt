package com.example.fintrack

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fintrack.ui.sections.ChartSection
import com.example.fintrack.ui.sections.RecipientsSection
import com.example.fintrack.ui.sections.TransactionsSection
import com.example.fintrack.ui.utils.Constants.Texts.ADD_ACTION
import com.example.fintrack.ui.utils.Constants.Texts.ANALYTICS_MESSAGE
import com.example.fintrack.ui.utils.Constants.Texts.BALANCE_AMOUNT
import com.example.fintrack.ui.utils.Constants.Texts.BALANCE_LABEL
import com.example.fintrack.ui.utils.Constants.Texts.HOME_BUTTON_LABEL
import com.example.fintrack.ui.utils.Constants.Texts.NOTIFICATION_ICON
import com.example.fintrack.ui.utils.Constants.Texts.PROFILE_MESSAGE
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Text(
                        text = WELCOME_MESSAGE,
                        style = MaterialTheme.typography.bodyLarge.copy()
                    )
                    Text(
                        text = "John!",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Box {
                    Text(
                        text = NOTIFICATION_ICON,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .offset(x = 8.dp, y = 0.dp)
                            .background(Color.Red, shape = RoundedCornerShape(50))
                    )
                }
            }

            Spacer(modifier = Modifier.height(padding))
            HorizontalDivider(
                thickness = 1.dp,
                color = colorScheme.secondary.copy(alpha = 0.3f)
            )
            Spacer(modifier = Modifier.height(padding))

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

            Spacer(modifier = Modifier.height(padding))

            ChartSection()

            Spacer(modifier = Modifier.height(padding))

            RecipientsSection()

            Spacer(modifier = Modifier.height(padding))

            TransactionsSection()

            Spacer(modifier = Modifier.height(padding))
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = padding)
        ) {
            BottomMenu()
        }

        FloatingActionButton(
            onClick = { Toast.makeText(context, ADD_ACTION, Toast.LENGTH_SHORT).show() },
            shape = CircleShape,
            containerColor = colorScheme.primary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 0.dp, bottom = padding)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "Add",
                tint = colorScheme.background
            )
        }
    }
}

@Composable
fun BottomMenu() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .background(Color.Black, shape = RoundedCornerShape(32.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(32.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = "Home",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = HOME_BUTTON_LABEL,
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(onClick = { Toast.makeText(context, ANALYTICS_MESSAGE, Toast.LENGTH_SHORT).show() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chart),
                contentDescription = "Charts",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }

        IconButton(onClick = { Toast.makeText(context, PROFILE_MESSAGE, Toast.LENGTH_SHORT).show() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_user),
                contentDescription = "Profile",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
