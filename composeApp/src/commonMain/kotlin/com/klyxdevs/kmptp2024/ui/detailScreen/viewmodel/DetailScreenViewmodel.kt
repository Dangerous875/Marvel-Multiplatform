package com.klyxdevs.kmptp2024.ui.detailScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klyxdevs.kmptp2024.domain.model.CharacterDomain
import com.klyxdevs.kmptp2024.domain.usecases.GetCharacterByIDUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailScreenViewmodel(private val getCharacterByIDUseCase: GetCharacterByIDUseCase) :
    ViewModel() {

    private val _characterSelected = MutableStateFlow<CharacterDomain?>(null)
    val characterSelected = _characterSelected.asStateFlow()
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    fun getHeroByID(id: Long) {
        viewModelScope.launch {
            _characterSelected.value = getCharacterByIDUseCase(id)
            if (_characterSelected.value != null) {
                _isLoading.value = false
            }
        }
    }
}