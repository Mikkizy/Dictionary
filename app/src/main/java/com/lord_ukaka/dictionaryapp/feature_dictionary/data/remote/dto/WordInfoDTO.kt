package com.lord_ukaka.dictionaryapp.feature_dictionary.data.remote.dto

import com.lord_ukaka.dictionaryapp.feature_dictionary.data.local.entity.WordInfoEntity
import com.lord_ukaka.dictionaryapp.feature_dictionary.domain.model.WordInfo

data class WordInfoDTO(
    val license: LicenseDTO,
    val meanings: List<MeaningDTO>,
    val phonetic: String,
    val phonetics: List<PhoneticDTO>,
    val sourceUrls: List<String>,
    val word: String
) {
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meanings = meanings.map { it.toMeaning() },
            phonetic = phonetic,
            word = word
        )
    }

}