package com.juanlucena.personsproject.ui.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.juanlucena.personsproject.ui.theme.Blue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    title: String,
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar (
        title = { Text(text = title, color = Color.White) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Blue),
        navigationIcon = navigationIcon ?: {},
        actions = actions
    )
}