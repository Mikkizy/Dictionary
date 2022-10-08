package com.lord_ukaka.dictionaryapp.feature_dictionary.domain.repository

import com.lord_ukaka.dictionaryapp.core.utils.Resource
import com.lord_ukaka.dictionaryapp.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow


interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>

}