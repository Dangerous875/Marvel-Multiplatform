package com.klyxdevs.kmptp2024.ui.mainScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.klyxdevs.kmptp2024.domain.model.CharacterDomain
import com.klyxdevs.kmptp2024.domain.usecases.GetCharactersFromApiUseCase
import com.klyxdevs.kmptp2024.domain.usecases.GetCharactersFromSQLDelight
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val getCharactersFromApiUseCase: GetCharactersFromApiUseCase,
    private val getCharactersFromSQLDelight: GetCharactersFromSQLDelight
) : ViewModel() {

    private val _characters = MutableStateFlow<List<CharacterDomain>>(emptyList())
    val characters = _characters.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        viewModelScope.launch {
            try {
                val result = getCharactersFromApiUseCase()
                _characters.value = result.ifEmpty { getCharactersFromSQLDelight() }
            } catch (e: Exception) {
                Logger.i("Inyeccion -> DATABASE LOAD")
                val result = getCharactersFromSQLDelight()
                _characters.value = result.ifEmpty { emptyList() }
            } finally {
                _isLoading.value = false
            }
        }
    }
}