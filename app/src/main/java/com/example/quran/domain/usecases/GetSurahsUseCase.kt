package com.example.quran.domain.usecases

import com.example.quran.domain.repository.SurahRepository

class GetSurahsUseCase(
    private val surahRepository: SurahRepository
) {
    suspend operator fun invoke(surahNumber: Int) = surahRepository.getSurahs(surahNumber = surahNumber)
}