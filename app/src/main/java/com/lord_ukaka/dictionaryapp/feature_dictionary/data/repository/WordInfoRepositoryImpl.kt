package com.lord_ukaka.dictionaryapp.feature_dictionary.data.repository

import com.lord_ukaka.dictionaryapp.core.utils.Resource
import com.lord_ukaka.dictionaryapp.feature_dictionary.data.local.WordInfoDao
import com.lord_ukaka.dictionaryapp.feature_dictionary.data.remote.DictionaryApi
import com.lord_ukaka.dictionaryapp.feature_dictionary.domain.model.WordInfo
import com.lord_ukaka.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())
        val wordInfos = dao.getWordInfos(word).map {
            it.toWordInfo()
        }
        emit(Resource.Loading(data = wordInfos))
        try {
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(
                remoteWordInfos. map {
                it.word
            })
            dao.insertWordInfos(remoteWordInfos.map {
                it.toWordInfoEntity()
            })
        } catch (e: HttpException) {
            emit(Resource.Error(
                "Oops! something went wrong!",
                data = wordInfos
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                "Couldn't reach server, kindly check your internet connection.",
                data = wordInfos
            ))
        }
        val newWordInfos = dao.getWordInfos(word).map {
            it.toWordInfo()
        }
        emit(Resource.Success(newWordInfos))
    }
}