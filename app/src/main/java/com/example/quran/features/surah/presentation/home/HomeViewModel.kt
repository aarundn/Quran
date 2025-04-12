package com.example.quran.features.surah.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quran.data.local.SurahDao
import com.example.quran.domain.repository.SurahRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val surahRepository: SurahRepository,
    private val surahDao: SurahDao
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getSurahs()
    }

    fun getSurahs() {
        viewModelScope.launch(IO) {
            _state.update { it.copy(isLoading = true) }
            if (surahDao.getAllSurah().isEmpty()) {
                surahRepository.getSurahs().collect { surahs ->
                    Log.d("HomeViewModel", "getSurahs: $surahs")
                    surahDao.insertSurah(surahs)
                    _state.update {
                        it.copy(
                            isLoading = false,
                            surahs = surahDao.getAllSurah().ifEmpty { surahs }
                        )
                    }

                }
            } else {

                _state.update {
                    it.copy(
                        isLoading = false,
                        surahs = surahDao.getAllSurah()
                    )
                }
            }
        }

    }
//    fun loadMoreSurahs(surahNumber: Int){
//        viewModelScope.launch {
//            _state.update { it.copy(isLoadingMore = true) }
//            surahRepository.loadMoreSurahs(surahNumber).collect{ nenwSurahs ->
//                Log.d("HomeViewModel1", "loadMoreSurahs: $nenwSurahs")
//                _state.update { it.copy(
//                    isLoadingMore = false,
//                    surahs = it.surahs + nenwSurahs
//                ) }
//                Log.d("HomeViewModel1", "loadMoreSurahs: ${_state.value.surahs}")
//            }
//        }
//    }
}