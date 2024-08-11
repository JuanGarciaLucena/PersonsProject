package com.juanlucena.personsproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.juanlucena.personsproject.ui.screens.favoritesScreen.FavoritesScreen
import com.juanlucena.personsproject.ui.screens.personDetailScreen.PersonDetail
import com.juanlucena.personsproject.ui.screens.personListScreen.PersonListScreen
import com.juanlucena.personsproject.ui.viewmodel.PersonViewModel


@Composable
fun MyAppNavHost() {
    val navController = rememberNavController()
    val personViewModel: PersonViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "PersonListScreen") {



        composable(route = "PersonListScreen") {
            PersonListScreen(navController, personViewModel)
        }
        composable(route = "PersonDetailScreen") {
            PersonDetail(navController, personViewModel)
        }
        composable(route = "FavoritesScreen") {
            FavoritesScreen(navController, personViewModel)
        }
    }
}