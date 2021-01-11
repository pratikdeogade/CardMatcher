package com.pi.cardmatcher.data.model

data class MatcherModelResponse(
    val results: List<CardMatcherModel>,
    val info: Info
)