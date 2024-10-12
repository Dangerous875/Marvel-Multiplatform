package com.klyxdevs.kmptp2024.ui.homeScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klyxdevs.kmptp2024.domain.model.CharacterDomain
import com.klyxdevs.kmptp2024.domain.usecases.GetCharactersFromApiUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeScreenViewModel(private val getCharactersFromApiUseCase: GetCharactersFromApiUseCase) : ViewModel() {
    private val _characters = MutableStateFlow<List<CharacterDomain>>(emptyList())
    val characters = _characters.asStateFlow()
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                getCharactersFromApiUseCase()
            }
            if (result.isNotEmpty()){
                _characters.value = result
                _isLoading.value = false
            }
        }
    }
}
