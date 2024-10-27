package com.klyxdevs.kmptp2024.ui.Test

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun Test() {
    val viewModel = koinViewModel<TestViewModel>()
    val users by viewModel.users.collectAsState()

    LazyColumn {
        items(users) {
            Text("${it.id} ... ${it.name}")
        }
    }
}