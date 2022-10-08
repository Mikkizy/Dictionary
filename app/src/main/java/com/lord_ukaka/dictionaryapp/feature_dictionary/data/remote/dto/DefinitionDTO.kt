package com.lord_ukaka.dictionaryapp.feature_dictionary.data.remote.dto

import com.lord_ukaka.dictionaryapp.feature_dictionary.domain.model.Definition

data class DefinitionDTO(
    val antonyms: List<String>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
) {
    fun toDefinition(): Definition {
        return Definition(
            antonyms = antonyms,
            definition = definition,
            example = example,
            synonyms = synonyms
        )
    }
}