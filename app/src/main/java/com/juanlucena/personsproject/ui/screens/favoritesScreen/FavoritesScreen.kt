package com.juanlucena.personsproject.ui.screens.favoritesScreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.juanlucena.personsproject.R
import com.juanlucena.personsproject.domain.PersonModel
import com.juanlucena.personsproject.ui.common.MyNavigationBar
import com.juanlucena.personsproject.ui.common.MyToolbar
import com.juanlucena.personsproject.ui.screens.commonScreen.ErrorScreen
import com.juanlucena.personsproject.ui.screens.commonScreen.LoadingScreen
import com.juanlucena.personsproject.ui.states.PersonListUiState
import com.juanlucena.personsproject.ui.theme.Blue
import com.juanlucena.personsproject.ui.viewmodel.PersonViewModel

@Composable
fun FavoritesScreen(navController: NavController, personViewModel: PersonViewModel) {

    val uiState by personViewModel.savedPersonsInDb.collectAsState()
    var personList: List<PersonModel>
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(Unit) {
        personViewModel.getSavedPersons()
    }

    Scaffold(
        bottomBar = {
            MyNavigationBar(
                currentRoute = currentRoute ?: "",
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        },
        topBar = { MyToolbar(title = stringResource(id = R.string.app_name)) })
    { paddingValues ->
        paddingValues.calculateBottomPadding()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState) {
                is PersonListUiState.Loading -> {
                    LoadingScreen()
                }

                is PersonListUiState.SuccessDb -> {
                    Log.i(
                        "LIST_SCREEN",
                        (uiState as PersonListUiState.SuccessDb).data.toString()
                    )
                    personList = (uiState as PersonListUiState.SuccessDb).data
                    if (personList.isEmpty()) {
                        ErrorScreen(
                            iconId = R.drawable.ic_empty_list,
                            message = stringResource(id = R.string.default_empty_favorite_list_label)
                        )
                    } else {
                        FillFavoriteList(navController = navController, personList = personList, personViewModel = personViewModel)
                    }
                }

                is PersonListUiState.Error -> {
                    ErrorScreen(iconId = R.drawable.ic_error, message = "")
                }

                else -> {}
            }
        }
    }
}

@Composable
fun FillFavoriteList(navController: NavController, personList: List<PersonModel>, personViewModel: PersonViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        items(items = personList, itemContent = { personItem ->
            FavoriteListItemView(navController = navController, person = personItem, viewModel = personViewModel)
        })
    }
}

@Composable
fun FavoriteListItemView(navController: NavController, person: PersonModel, viewModel: PersonViewModel) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = Modifier.padding(10.dp)) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Blue
            ),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .shadow(elevation = 10.dp)
                .clickable {
                    keyboardController?.hide()
                    viewModel.selectPerson(person)
                    navController.navigate("PersonDetailScreen")
                }
        ) {

            Row {
                AsyncImage(
                    model = person.picture.medium,
                    contentDescription = null,
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                        .padding(10.dp),
                    contentScale = ContentScale.Fit,
                    error = painterResource(id = R.drawable.ic_placeholder)
                )

                Column(
                    modifier = Modifier
                        .height(150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${person.name.title} ${person.name.first} ${person.name.last}",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        color = Color.White,
                    )

                    Text(
                        text = person.email,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Color.White
                    )
                }
            }
        }
    }
}