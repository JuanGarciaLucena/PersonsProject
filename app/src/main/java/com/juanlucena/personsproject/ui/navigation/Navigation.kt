package com.juanlucena.personsproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.juanlucena.personsproject.ui.screens.FavoritesScreen
import com.juanlucena.personsproject.ui.screens.PersonDetail
import com.juanlucena.personsproject.ui.screens.PersonListScreen
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
            FavoritesScreen(navController)
        }
    }
}