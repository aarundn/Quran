package com.example.quran.domain.usecases

import com.example.quran.domain.repository.SurahRepository
import kotlinx.coroutines.flow.flow

class GetSurahDetailsUseCase(
    private val surahRepository: SurahRepository
) {
    suspend operator fun invoke(surahNumber: Int) = flow {
        emit(surahRepository.getSurahDetails(surahNumber))
    }
}