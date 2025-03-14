package com.example.quran.features.surah.presentation.home

import android.util.Log
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

    fun getSurahs(surahNumber: Int){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            surahRepository.getSurahs(surahNumber).collect{ surahs ->
                Log.d("HomeViewModel", "getSurahs: $surahs")
                _state.update { it.copy(
                    isLoading = false,
                    surahs = surahs
                ) }
            }
        }

    }
}