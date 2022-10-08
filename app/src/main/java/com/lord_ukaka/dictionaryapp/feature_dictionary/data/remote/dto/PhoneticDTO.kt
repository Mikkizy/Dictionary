package com.lord_ukaka.dictionaryapp.feature_dictionary.data.remote.dto

data class PhoneticDTO(
    val audio: String,
    val license: LicenseDTO,
    val sourceUrl: String,
    val text: String
)