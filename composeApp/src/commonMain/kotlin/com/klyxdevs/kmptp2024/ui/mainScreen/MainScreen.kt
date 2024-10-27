package com.klyxdevs.kmptp2024.ui.mainScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.klyxdevs.kmptp2024.ui.components.ExitConfirmation
import com.klyxdevs.kmptp2024.ui.core.navigation.Routes
import com.klyxdevs.kmptp2024.ui.mainScreen.viewmodel.MainScreenViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MainScreen(navController: NavController) {
    val mainScreenViewModel = koinViewModel<MainScreenViewModel>()
    val isLoading by mainScreenViewModel.isLoading.collectAsState()

    if (isLoading) {
        Box(
            Modifier.fillMaxSize().background(
                Brush.verticalGradient(
                    listOf(Color.LightGray, Color.Black),
                    startY = 0f,
                    endY = 600f
                )
            ), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.Red)
        }
    } else {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar(navController) },
            content = { ContentView(mainScreenViewModel, navController) })
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
    var showExitConfirmation by rememberSaveable { mutableStateOf(false) }

    ExitConfirmation(
        show = showExitConfirmation,
        onDismiss = { showExitConfirmation = false },
        onConfirm = {
            navController.navigate(Routes.HomeScreenRoute) {
                popUpTo<Routes.HomeScreenRoute> { inclusive = true }
            }
        },
        title = "Exit Confirmation",
        message = "Do you want back to Home ? "
    )

    TopAppBar(
        modifier = Modifier.height(48.dp),
        title = {
            Text(
                text = "Hero List - Click for Detail",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textAlign = TextAlign.Start,
                color = Color.White,
                fontStyle = FontStyle.Italic
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Black), navigationIcon = {
            IconButton(onClick = {  showExitConfirmation = true  }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

    )
}

@Composable
fun ContentView(mainScreenViewModel: MainScreenViewModel, navController: NavController) {
    val characters by mainScreenViewModel.characters.collectAsState()
    Box(
        Modifier.fillMaxSize().background(Color.Black).padding(top = 48.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(characters) { hero ->
                    Card(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .height(350.dp)
                            .clickable { navController.navigate(Routes.DetailScreenRoute(hero.id)) },
                        shape = RoundedCornerShape(12),
                        border = BorderStroke(4.dp, Color.White)
                    ) {
                        Box(contentAlignment = Alignment.CenterStart) {
                            Box(
                                modifier = Modifier.fillMaxSize()
                                    .background(Color.Green.copy(alpha = 0.5f))
                            )

                            AsyncImage(
                                model = hero.imageURL,
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )

                            Box(
                                modifier = Modifier.fillMaxSize()
                                    .background(
                                        Brush.horizontalGradient(
                                            0f to Color.Black.copy(alpha = 0.9f),
                                            0.4f to Color.White.copy(alpha = 0f)
                                        )
                                    )
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .align(Alignment.BottomCenter)
                                    .background(com.klyxdevs.kmptp2024.ui.core.Card)
                            ) {
                                Text(
                                    text = hero.name,
                                    fontSize = 23.sp,
                                    modifier = Modifier.align(Alignment.Center),
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White,
                                    maxLines = 1,
                                    minLines = 1,
                                    textAlign = TextAlign.Center,
                                    overflow = TextOverflow.Ellipsis
                                )

                            }
                        }
                    }
                }
            }
        }

    }
}
