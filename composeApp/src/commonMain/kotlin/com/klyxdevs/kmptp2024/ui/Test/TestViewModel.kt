package com.klyxdevs.kmptp2024.ui.Test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klyxdevs.kmptp2024.DatabaseDriverFactory
import com.klyxdevs.kmptp2024.SuperHeroDB
import com.klyxdevs.kmptp2024.SuperHeroDBQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TestViewModel(driverFactory: DatabaseDriverFactory) : ViewModel() {

    private val _users = MutableStateFlow<List<UserDataBase>>(emptyList())
    val users = _users.asStateFlow()

    // Inicializa la base de datos
    private val db = SuperHeroDB(driverFactory.createDriver())
    private val query: SuperHeroDBQueries = db.superHeroDBQueries

    init {
        // Carga los datos de la base de datos al iniciar el ViewModel
        loadUsersFromDB()
    }

    private fun loadUsersFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            val usersInDB: List<UserDataBase> = query.getAll { id: Long, name: String ->
                UserDataBase(id, name)
            }.executeAsList()

            _users.emit(usersInDB) // Emite la lista de usuarios en el flujo
        }
    }
}

data class UserDataBase(val id: Long, val name: String)