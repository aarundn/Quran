package com.example.quran.features.surah.presentation.components

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.RoundedPolygon
import com.example.quran.R
import kotlin.math.sqrt

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
        .clickable { onClick() }) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier.padding(8.dp).size(40.dp)) {
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
    versesCount: Int
) {
    Column(modifier = modifier) {
        Text(
            text = englishName,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.labelMedium,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.wrapContentWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = surahType,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelSmall,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Spacer(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(4.dp)
                    .background(color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f))
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "$versesCount VERSES",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelSmall,
            )
        }

    }
}
/**@Composable
//fun StarOfDavid(
//    modifier: Modifier = Modifier,
//    color: Color = Color.Black,
//    strokeWidth: Float
//) {
//    Canvas(
//        modifier = modifier
//    ) {
//        val width = size.width
//        val height = size.height
//        val minDimension = minOf(width, height)
//
//        // Scale everything to fit within the available space
//        val scale = minDimension / 100f
//
//        val centerX = width / 2f
//        val centerY = height / 2f
//
//        // Create the octagon star path
//        val starPath = Path().apply {
//            // Starting from top point
//            moveTo(centerX, centerY - 40f * scale)  // Top
//            lineTo(centerX + 20f * scale, centerY - 20f * scale)  // Top-right corner
//            lineTo(centerX + 40f * scale, centerY)  // Right
//            lineTo(centerX + 20f * scale, centerY + 20f * scale)  // Bottom-right corner
//            lineTo(centerX, centerY + 40f * scale)  // Bottom
//            lineTo(centerX - 20f * scale, centerY + 20f * scale)  // Bottom-left corner
//            lineTo(centerX - 40f * scale, centerY)  // Left
//            lineTo(centerX - 20f * scale, centerY - 20f * scale)  // Top-left corner
//            close()
//        }
//
//        // Draw the outline
//        drawPath(
//            path = starPath,
//            color = color,
//            style = Stroke(
//                width = strokeWidth,
//                join = StrokeJoin.Round,
//                cap = StrokeCap.Round
//            )
//        )
//    }
//}
//
//@Composable
//fun OctagonStarOutline(modifier: Modifier = Modifier, color: Color, shapeSize: Dp, strokeWidth: Dp) {
//    Canvas(modifier = modifier.size(shapeSize)) {
//        val path = Path().apply {
//            val center = Offset(size.width / 2, size.height / 2)
//            val radius = size.minDimension / 2
//
//            for (i in 0 until 8) {
//                val angle = Math.PI / 4 * i
//                val x = (center.x + radius * Math.cos(angle)).toFloat()
//                val y = (center.y + radius * Math.sin(angle)).toFloat()
//                if (i == 0) moveTo(x, y) else lineTo(x, y)
//            }
//            close()
//        }
//
//        drawPath(
//            path = path,
//            color = color,
//            style = Stroke(strokeWidth.toPx())
//        )
//    }
//}
**/