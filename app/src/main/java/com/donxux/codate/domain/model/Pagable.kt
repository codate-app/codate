package com.donxux.codate.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Pagable(
    val data: JsonElement,
    val key: Int,
    val hasNext: Boolean,
    val count: Int
)
