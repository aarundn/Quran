package com.example.quran.common.components


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.quran.core.ui.theme.QuranTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    rightIcon: ImageVector,
    leftIcon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.background,
        ),
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onClick()}) {
                Icon(
                    imageVector = rightIcon,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = modifier
                        .size(40.dp)
                        .padding(8.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = leftIcon,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = modifier
                        .size(40.dp)
                        .padding(8.dp)
                )
            }
        },
    )
}


@Preview
@Composable
private fun TopAppBarPreview() {
    QuranTheme {
        TopAppBar(
            title = "Quran App",
            rightIcon = Icons.Filled.Menu,
            leftIcon = Icons.Filled.Search,
            onClick = {}
        )
    }
}