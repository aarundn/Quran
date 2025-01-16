package com.example.quran.features.surah.presentation.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quran.domain.model.Ayahs
import com.example.quran.domain.usecases.GetSurahDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsUseCase: GetSurahDetailsUseCase
): ViewModel() {
    val _ayahs = MutableStateFlow<List<Ayahs>>(emptyList())
    val ayahs = _ayahs.asStateFlow()
    init {
    }
    suspend fun getSurahDetails(surahNumber: Int) {
        detailsUseCase.invoke(surahNumber).collect{
            _ayahs.value = it.first()
        }
    }

}