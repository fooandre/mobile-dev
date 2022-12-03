package com.example.assignment.models

class Movie(
    var _id: Long,
    var title: String,
    var description: String,
    var releaseDate: String,
    var violence: Boolean = false,
    var languageUsed: Boolean = false,
    var language: Language = Language.ENGLISH
)

enum class Language { ENGLISH, CHINESE, MALAY, TAMIL }