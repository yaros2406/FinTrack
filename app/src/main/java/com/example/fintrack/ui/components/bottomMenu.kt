package com.example.fintrack.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fintrack.R
import com.example.fintrack.ui.utils.Constants.Dimensions.ROUNDED_CORNER_MENU
import com.example.fintrack.ui.utils.Constants.Texts.ANALYTICS_MESSAGE
import com.example.fintrack.ui.utils.Constants.Texts.HOME_BUTTON_LABEL
import com.example.fintrack.ui.utils.Constants.Texts.PROFILE_MESSAGE
import com.example.fintrack.ui.utils.MaterialThemeSpacing.spacing

@Composable
fun BottomMenu() {
    val context = LocalContext.current
    val color = MaterialTheme.colorScheme

    Row(
        modifier = Modifier
            .background(color.primary, shape = RoundedCornerShape(ROUNDED_CORNER_MENU.dp))
            .padding(horizontal = spacing.small, vertical = spacing.extraSmall),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .background(color.background, shape = RoundedCornerShape(ROUNDED_CORNER_MENU.dp))
                .padding(horizontal = spacing.medium, vertical = spacing.default),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = "Home",
                tint = color.primary,
                modifier = Modifier.size(spacing.large)
            )
            Spacer(modifier = Modifier.width(spacing.small))
            Text(
                text = HOME_BUTTON_LABEL,
                color = color.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.width(spacing.small))

        IconButton(onClick = { Toast.makeText(context, ANALYTICS_MESSAGE, Toast.LENGTH_SHORT).show() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chart),
                contentDescription = "Charts",
                tint = color.background,
                modifier = Modifier.size(spacing.large)
            )
        }

        IconButton(onClick = { Toast.makeText(context, PROFILE_MESSAGE, Toast.LENGTH_SHORT).show() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_user),
                contentDescription = "Profile",
                tint = color.background,
                modifier = Modifier.size(spacing.large)
            )
        }
    }
}