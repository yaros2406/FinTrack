package com.example.fintrack.ui.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fintrack.R
import com.example.fintrack.ui.utils.Constants.Dimensions.AVATAR_SIZE
import com.example.fintrack.ui.utils.Constants.Texts.RECIPIENTS_EXTRA_COUNT
import com.example.fintrack.ui.utils.Constants.Texts.RECIPIENTS_TITLE
import com.example.fintrack.ui.utils.MaterialThemeSpacing.spacing

@Composable
fun RecipientsSection() {
    Column {
        Text(
            text = RECIPIENTS_TITLE,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = spacing.small)
        )
        Spacer(modifier = Modifier.height(spacing.small))
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.medium),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(4) {
                Box(
                    modifier = Modifier
                        .size(AVATAR_SIZE.dp)
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_placeholder),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(AVATAR_SIZE.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(AVATAR_SIZE.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.onSurfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = RECIPIENTS_EXTRA_COUNT,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
