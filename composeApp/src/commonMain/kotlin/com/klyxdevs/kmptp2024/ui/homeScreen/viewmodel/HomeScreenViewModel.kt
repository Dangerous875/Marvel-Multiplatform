package com.klyxdevs.kmptp2024.ui.homeScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klyxdevs.kmptp2024.DatabaseDriverFactory
import com.klyxdevs.kmptp2024.Greeting
import com.klyxdevs.kmptp2024.SuperHeroDB
import com.klyxdevs.kmptp2024.ui.homeScreen.local.WallpaperLogos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val wallpaperLogos: WallpaperLogos,
    greeting: Greeting,
    private val databaseDriverFactory: DatabaseDriverFactory
) :
    ViewModel() {

    private val _logos = MutableStateFlow(wallpaperLogos.logos[0])
    val logos = _logos.asStateFlow()
    private val _platformName = MutableStateFlow(greeting.greet())
    val platformName = _platformName.asStateFlow()
    private var initRandomLogo = true

    init {
        chargeDB()
        viewModelScope.launch {
            while (initRandomLogo) {
                delay(5000)
                val list = wallpaperLogos.logos
                val randomNumber = (list.indices).random()
                _logos.value = list[randomNumber]
            }
        }
    }

    private fun chargeDB() {
        viewModelScope.launch(Dispatchers.IO) { // Ejecuta en un hilo de fondo
            val db = databaseDriverFactory.createDriver()
            val db2 = SuperHeroDB(db)
            val query = db2.superHeroDBQueries

            query.transaction { // Inicia una transacción
                for (i in 0..9) {
                    query.insert(id = i.toLong(), name = "Cris")
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}
