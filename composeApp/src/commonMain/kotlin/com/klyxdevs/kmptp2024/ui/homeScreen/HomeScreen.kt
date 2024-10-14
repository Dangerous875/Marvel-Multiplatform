package com.klyxdevs.kmptp2024.ui.homeScreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.klyxdevs.kmptp2024.ui.components.ButtonWithBackgroundImage
import com.klyxdevs.kmptp2024.ui.components.ExitConfirmation
import com.klyxdevs.kmptp2024.ui.core.navigation.Routes
import com.klyxdevs.kmptp2024.ui.homeScreen.viewmodel.HomeScreenViewModel
import kmptp2024.composeapp.generated.resources.Res
import kmptp2024.composeapp.generated.resources.iv_button
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(navController: NavController) {
    val homeScreenViewModel = koinViewModel<HomeScreenViewModel>()
    val platform = homeScreenViewModel.platformName.collectAsState()
    var changeColor by remember { mutableStateOf(false) }

    val animatedColor by animateColorAsState(
        targetValue = if (changeColor) Color.White else Color.Red,
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 1000), label = ""
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(1500)
            changeColor = !changeColor
        }
    }

    Scaffold(
        topBar = { TopBarHome(animatedColor) },
        content = {
            ContentViewHome(homeScreenViewModel, navController, platform, animatedColor)
        }

    )

}

@Composable
fun ContentViewHome(
    viewmodel: HomeScreenViewModel,
    navController: NavController,
    platform: State<String>,
    animatedColor: Color
) {
    val logos by viewmodel.logos.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp),
        contentAlignment = Alignment.Center
    )
    {


        Image(
            painter = painterResource(logos.logo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = platform.value,
            color = animatedColor,
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )

        ButtonWithBackgroundImage(
            imageResId = Res.drawable.iv_button,
            onClick = { navController.navigate(Routes.MainScreenRoute) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(300.dp)
                .height(80.dp)
                .padding(bottom = 22.dp)
        ) {
            Text(
                text = "Marvel List",
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Normal,
                fontSize = 28.sp,
                color = Color.Black
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHome(animatedColor: Color) {

    var showExitConfirmation by rememberSaveable { mutableStateOf(false) }

    ExitConfirmation(
        show = showExitConfirmation,
        onDismiss = { showExitConfirmation = false },
        onConfirm = { exitApp() },
        title = "Exit Confirmation",
        message = "Do you want to exit app ? "
    )

    TopAppBar(
        modifier = Modifier.height(48.dp),
        title = {
            Text(
                text = "Kotlin MultiPlatform TP 2024",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textAlign = TextAlign.Start,
                color = animatedColor,
                fontStyle = FontStyle.Italic
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Black),
        actions = {
            IconButton(onClick = { showExitConfirmation = true }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = null,
                    tint = animatedColor
                )
            }
        }
    )
}


expect fun exitApp()





