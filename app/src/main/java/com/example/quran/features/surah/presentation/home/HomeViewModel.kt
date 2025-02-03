package com.example.quran.features.surah.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quran.domain.repository.SurahRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val surahRepository: SurahRepository
): ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    init {
        getSurahs()
    }
    private fun getSurahs() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            surahRepository.getSurahs().collect{ surahs ->
                _state.update { it.copy(
                    isLoading = false,
                    surahs = surahs
                ) }
            }
        }

    }
}