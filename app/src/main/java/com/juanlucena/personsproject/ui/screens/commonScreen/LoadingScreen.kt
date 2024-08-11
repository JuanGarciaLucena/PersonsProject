package com.juanlucena.personsproject.ui.screens.commonScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    message: String = "Cargando..."
) {
    Box(
        modifier = modifier
            .fillMaxSize()  // Llena todo el tamaño disponible
            .background(MaterialTheme.colorScheme.background),  // Fondo de la pantalla
        contentAlignment = Alignment.Center  // Centra el contenido
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,  // Color del indicador de progreso
                strokeWidth = 4.dp  // Grosor del indicador
            )
            Spacer(modifier = Modifier.height(16.dp))  // Espacio entre el indicador y el texto
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,  // Estilo del texto
                color = MaterialTheme.colorScheme.onBackground  // Color del texto
            )
        }
    }
}