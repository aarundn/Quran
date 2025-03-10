package com.example.quran.features.surah.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.quran.R
import com.example.quran.domain.model.Ayahs

@Composable
fun AyahItem(
    modifier: Modifier = Modifier,
    showTimeLine: Boolean,
    currentPosition: Long,
    duration: Long,
    ayahs: Ayahs,
    @DrawableRes audioIcon: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconsLabel(
            number = ayahs.number ?: 0,
            onClick = onClick,
            audioIcon = audioIcon,
            currentPosition = currentPosition,
            duration = duration,
            showTimeLine = showTimeLine
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = ayahs.text ?: "",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Right,
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.height(8.dp))
        HorizontalDivider(
            modifier = modifier.background(MaterialTheme.colorScheme.secondary)
        )
    }
}

@Composable
fun IconsLabel(
    number: Int,
    showTimeLine: Boolean,
    onClick: () -> Unit,
    @DrawableRes audioIcon: Int,
    currentPosition: Long,
    duration: Long,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(15.dp)
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.surfaceContainer,
                    shape = RoundedCornerShape(15.dp)
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = modifier
                    .wrapContentSize()
                    .padding(8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
                    .size(48.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = number.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = modifier.weight(1f))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.share),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = modifier.size(16.dp))
            Icon(
                imageVector = ImageVector.vectorResource(audioIcon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = modifier.clickable {
                    onClick()
                }
            )
            Spacer(modifier = modifier.size(16.dp))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.bookmark),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = modifier.size(8.dp))
        }
        if (showTimeLine){
            LinearProgressIndicator(
                modifier = modifier.fillMaxWidth().padding(8.dp),
                progress = { currentPosition.toFloat()/ duration.toFloat()},
            )
        }
    }
}
