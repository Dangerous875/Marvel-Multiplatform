package com.klyxdevs.kmptp2024.ui.mainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.klyxdevs.kmptp2024.ui.navigation.Routes

@Composable
fun MainScreen(navController: NavController){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column {
            Text("MainScreen", color = Color.Black)
            Spacer(Modifier.size(30.dp))
            Button(onClick = {navController.navigate(Routes.DetailScreenRoute(id = 20L))}){
                Text("navigation")
            }
        }

    }
}