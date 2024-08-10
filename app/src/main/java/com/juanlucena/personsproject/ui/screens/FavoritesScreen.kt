package com.juanlucena.personsproject.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.juanlucena.personsproject.R
import com.juanlucena.personsproject.ui.common.MyNavigationBar
import com.juanlucena.personsproject.ui.common.MyToolbar

@Composable
fun FavoritesScreen(navController: NavController){
    Scaffold(
        bottomBar = {
            MyNavigationBar(
                currentRoute = "FavoritesScreen",
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        },
        topBar = { MyToolbar(title = stringResource(id = R.string.favorite_label)) }
    ){ paddingValues ->
        // Contenido de SettingsScreen con paddingValues
        Text(
            text = "SettingsScreen",
            modifier = Modifier.padding(paddingValues)
        )
    }
}