package com.klyxdevs.kmptp2024.ui.detailScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.klyxdevs.kmptp2024.ui.detailScreen.viewmodel.DetailScreenViewmodel
import kmptp2024.composeapp.generated.resources.Res
import kmptp2024.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailScreen(navController: NavController, heroID: Long) {
    val detailScreenViewmodel = koinViewModel<DetailScreenViewmodel>()
    detailScreenViewmodel.getHeroByID(heroID)
    val isLoading by detailScreenViewmodel.isLoading.collectAsState()
    val hero by detailScreenViewmodel.characterSelected.collectAsState()

    if (isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = hero!!.name)
                TextField(value = hero!!.imageURL, onValueChange = {})
                AsyncImage(
                    model = hero!!.imageURL,
                    contentDescription = null,
                    error = painterResource(Res.drawable.compose_multiplatform)
                )

                    }



        }
    }

}