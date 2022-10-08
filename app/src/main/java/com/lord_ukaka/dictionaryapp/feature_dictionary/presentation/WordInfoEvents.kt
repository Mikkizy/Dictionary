package com.lord_ukaka.dictionaryapp.feature_dictionary.presentation

sealed class WordInfoEvents {
    data class ShowSnackbar(val message: String): WordInfoEvents()
}
