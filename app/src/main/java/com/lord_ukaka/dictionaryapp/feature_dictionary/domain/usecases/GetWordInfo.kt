package com.lord_ukaka.dictionaryapp.feature_dictionary.domain.usecases

import com.lord_ukaka.dictionaryapp.core.utils.Resource
import com.lord_ukaka.dictionaryapp.feature_dictionary.domain.model.WordInfo
import com.lord_ukaka.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val repository: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {return  flow {  }}
        return repository.getWordInfo(word)
    }
}