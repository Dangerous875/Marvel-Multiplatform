package com.klyxdevs.kmptp2024.ui.mainScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.klyxdevs.kmptp2024.data.network.model.CharacterResult
import com.klyxdevs.kmptp2024.ui.core.navigation.Routes
import com.klyxdevs.kmptp2024.ui.homeScreen.viewmodel.HomeScreenViewModel
import com.klyxdevs.kmptp2024.ui.mainScreen.viewmodel.MainScreenViewModel
import kmptp2024.composeapp.generated.resources.Res
import kmptp2024.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MainScreen(navController: NavController) {
    val mainScreenViewModel = koinViewModel<MainScreenViewModel>()
    val characters by mainScreenViewModel.characters.collectAsState()
    val isLoading by mainScreenViewModel.isLoading.collectAsState()

    if (isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                //List
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(characters) { hero ->
                        Text(
                            text = hero.name,
                            modifier = Modifier.clickable {
                                navController.navigate(
                                    Routes.DetailScreenRoute(hero.id)
                                )
                            })
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