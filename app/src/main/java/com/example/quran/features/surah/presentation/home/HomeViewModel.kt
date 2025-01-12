package com.example.quran.features.surah.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quran.domain.model.Surah
import com.example.quran.domain.repository.SurahRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val surahRepository: SurahRepository
): ViewModel() {
    private val _surahs = MutableStateFlow<List<Surah>>(emptyList())
    val surahs = _surahs.asStateFlow()
    fun getSurahs() {
        viewModelScope.launch {
            surahRepository.getSurahs().collect{
                _surahs.value = it
            }
        }

    }
}