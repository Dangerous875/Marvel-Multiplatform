package com.klyxdevs.kmptp2024.ui.detailScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.klyxdevs.kmptp2024.ui.core.navigation.Routes
import com.klyxdevs.kmptp2024.ui.detailScreen.viewmodel.DetailScreenViewmodel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun DetailScreen(navController: NavController, heroID: Long) {
    val detailScreenViewmodel = koinViewModel<DetailScreenViewmodel>()
    detailScreenViewmodel.getHeroByID(heroID)
    val isLoading by detailScreenViewmodel.isLoading.collectAsState()
    val hero by detailScreenViewmodel.characterSelected.collectAsState()

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
        Box(Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {

            Card(
                modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp, vertical = 4.dp)
                    .background(com.klyxdevs.kmptp2024.ui.core.Card),
                border = BorderStroke(
                    4.dp,
                    Color.LightGray
                )
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        modifier = Modifier.fillMaxWidth().height(48.dp), border = BorderStroke(
                            4.dp,
                            Color.LightGray
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .background(Color.Black),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(
                                modifier = Modifier.align(Alignment.CenterStart),
                                onClick = {
                                    navController.navigate(Routes.MainScreenRoute) {
                                        popUpTo<Routes.MainScreenRoute> { inclusive = true }
                                    }
                                }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                            Text(
                                text = hero!!.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = Color.White
                            )
                        }
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth()
                            .height(200.dp).background(
                                Brush.horizontalGradient(
                                    listOf(Color.Black, com.klyxdevs.kmptp2024.ui.core.Card),
                                    startX = 0f,
                                    endX = 600f
                                )
                            ),
                        shape = RoundedCornerShape(12)
                    ) {
                        Box(contentAlignment = Alignment.BottomStart) {
                            Box(
                                modifier = Modifier.fillMaxSize()
                                    .background(com.klyxdevs.kmptp2024.ui.core.Card.copy(alpha = 0.5f))
                            )

                            AsyncImage(
                                model = hero!!.imageURL,
                                contentDescription = "",
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

                        }
                    }
                    Card(
                        modifier = Modifier.fillMaxSize(), border = BorderStroke(
                            4.dp,
                            Color.LightGray
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize().background(Color.Black),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.TopStart)
                                    .padding(start = 8.dp, top = 4.dp),
                                text = "Description :",
                                color = Color.White
                            )
                            Text(
                                modifier = Modifier.padding(start = 8.dp).align(Alignment.Center),
                                text = hero!!.description,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 24.sp
                            )
                        }
                    }
                }
            }
//                Text(
//                    text = hero!!.name)
//                TextField(value = hero!!.imageURL, onValueChange = {})
//                AsyncImage(
//                    model = hero!!.imageURL,
//                    contentDescription = null,
//                    error = painterResource(Res.drawable.compose_multiplatform)
//                )
        }

    }

}