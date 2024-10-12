package com.klyxdevs.kmptp2024.ui.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.klyxdevs.kmptp2024.ui.core.navigation.Routes
import com.klyxdevs.kmptp2024.ui.homeScreen.viewmodel.HomeScreenViewModel
import kmptp2024.composeapp.generated.resources.Res
import kmptp2024.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(navController: NavController){
    val charactersViewModel = koinViewModel<HomeScreenViewModel>()
    val characters by charactersViewModel.characters.collectAsState()
    val isLoading by charactersViewModel.isLoading.collectAsState()

    if (isLoading){
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    }else{
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally){

                //List
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(characters) { hero ->
                        Text(hero.name)
                        TextField(value = hero.imageURL, onValueChange = {})
                        AsyncImage(
                            model = hero.imageURL,
                            contentDescription = null,
                            error = painterResource(Res.drawable.compose_multiplatform)
                        )
                    }
                }
            }

        }
    }

}