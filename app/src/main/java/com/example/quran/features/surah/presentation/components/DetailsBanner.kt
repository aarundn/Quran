package com.example.quran.features.surah.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quran.R
import com.example.quran.core.ui.theme.QuranTheme
import com.example.quran.domain.model.ScreenBanner

@Composable
fun DetailsBanner(
    surahName: String,
    surahType: String,
    versesCount: Int,
    englishName: String,
    modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .height(327.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = surahName,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = englishName,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground,

                )
            Spacer(modifier = modifier.height(16.dp))
            HorizontalDivider(
                modifier = modifier
                    .padding(end = 30.dp, start = 30.dp)
                    .height(0.5.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = modifier.height(16.dp))
            EnglishLabel(
                modifier = modifier,
                englishName = "", versesCount = versesCount,
                surahType = surahType,
                screenBanner = ScreenBanner.DETAILS_BANNER
            )
            Spacer(modifier = modifier.height(34.dp))
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.bismillah),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DetailsBannerPreview() {
    QuranTheme {
        DetailsBanner(
            surahName = "الفاتحة",
            surahType = "Meccan",
            versesCount = 7,
            englishName = "Al-Fatihah"
        )
    }
}