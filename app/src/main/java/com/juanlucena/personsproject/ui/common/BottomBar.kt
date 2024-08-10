package com.juanlucena.personsproject.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MyNavigationBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("PersonListScreen") },
            selected = currentRoute == "PersonListScreen",
            onClick = { onNavigate("PersonListScreen") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Favorites") },
            label = { Text("Favorites") },
            selected = currentRoute == "FavoritesScreen",
            onClick = { onNavigate("FavoritesScreen") }
        )
    }
}