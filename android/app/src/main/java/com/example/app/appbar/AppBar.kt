package com.example.app.appbar

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    appBarTitle: String,
    onNavigationIconClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.background(
            Brush.horizontalGradient(colors = listOf(
                Color(0xFFD06400),
                Color(0xFFA75000)
            ))
        ),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent
        ),
        title = {
            Text(text = appBarTitle, color = MaterialTheme.colorScheme.background)
        },
        navigationIcon = {
            IconButton(onClick =  onNavigationIconClick ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.Black)
            }
        }
    )
}