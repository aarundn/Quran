package com.example.quran.features.surah.presentation.components

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quran.R
import com.example.quran.domain.model.ScreenBanner

@Composable
fun SurahItem(
    modifier: Modifier = Modifier,
    number: Int,
    arabicName: String,
    englishName: String,
    surahType: String,
    versesCount: Int,
    onClick: () -> Unit,
) {
    Column(modifier = modifier
        .padding(4.dp)
        .clickable { onClick() }
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(40.dp)
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.octagon),
                    contentDescription = "Star",
                    modifier = Modifier.size(50.dp),
                )
                Text(
                    text = number.toString(),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            EnglishLabel(
                modifier = Modifier.weight(1f),
                englishName = englishName,
                surahType = surahType,
                versesCount = versesCount,
                screenBanner = ScreenBanner.HOME_BANNER
            )
            Text(
                text = arabicName,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider()
    }

}

@Composable
fun EnglishLabel(
    modifier: Modifier,
    englishName: String,
    surahType: String,
    versesCount: Int,
    screenBanner: ScreenBanner
) {
    var isHomeScreen by remember { mutableStateOf(true) }
    isHomeScreen = when (screenBanner) {
        ScreenBanner.HOME_BANNER -> {
            true
        }

        ScreenBanner.DETAILS_BANNER -> {
            false
        }
    }
    Column(modifier = modifier) {
        if (ScreenBanner.HOME_BANNER == screenBanner) {
            Text(
                text = englishName,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelMedium,
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.wrapContentWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val color =if (isHomeScreen) MaterialTheme.colorScheme.secondary else
                MaterialTheme.colorScheme.onBackground
            Text(
                text = surahType,
                color = color,
                style = MaterialTheme.typography.labelSmall,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Spacer(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(4.dp)
                    .background(color = color.copy(alpha = 0.5f))
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "$versesCount VERSES",
                color = color,
                style = MaterialTheme.typography.labelSmall,
            )
        }

    }
}
