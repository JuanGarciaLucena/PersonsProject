package com.juanlucena.personsproject.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.juanlucena.personsproject.R
import com.juanlucena.personsproject.ui.common.AppToolbar
import com.juanlucena.personsproject.ui.theme.BlueLight
import com.juanlucena.personsproject.ui.viewmodel.PersonViewModel

@Composable
fun PersonDetail(navController: NavController, personViewModel: PersonViewModel){

    val selectedPerson by personViewModel.selectedPerson.collectAsState()

    Scaffold(
        bottomBar = { },
        topBar = {
            AppToolbar(
                title = "",
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.content_description_back_nav),
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Acción para guardar en favoritos */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_favorite_empty),
                            contentDescription = stringResource(id = R.string.content_description_add_favorite),
                            tint = Color.White
                        )
                    }
                }
            )
        }
    )
    {

        it.calculateBottomPadding()


        MaterialTheme {
            Box(
                Modifier.padding(top = 110.dp)
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    AsyncImage(
                        model = selectedPerson?.picture?.medium,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(175.dp),
                        contentScale = ContentScale.Crop,
                        error = painterResource(id = R.drawable.ic_placeholder)
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                            .align(Alignment.CenterHorizontally),
                        text = "${selectedPerson?.name?.title} " +
                                "${selectedPerson?.name?.first} " +
                                "${selectedPerson?.name?.last}",
                        color = Color.Black, fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "${selectedPerson?.email}",
                        color = Color.Black, fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )

                    Box(modifier = Modifier.fillMaxWidth()) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = BlueLight
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp, end = 15.dp, top = 20.dp),
                            shape = RoundedCornerShape(5.dp)
                        ) {
                            Column(Modifier.padding(20.dp)) {
                                Text(
                                    text = stringResource(id = R.string.detail_person_info_label),
                                    fontSize = 25.sp,
                                    modifier = Modifier.padding(bottom = 10.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = stringResource(
                                        R.string.detail_person_phone_label,
                                        selectedPerson?.phone.toString()
                                    )
                                )
                                Text(
                                    text = stringResource(
                                        R.string.detail_person_address_label,
                                        selectedPerson?.location?.street?.name.toString(),
                                        selectedPerson?.location?.street?.number.toString(),
                                        selectedPerson?.location?.city.toString()
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}