package com.donxux.codate.domain.model

import java.util.UUID

data class User(
    val id: String,
    val name: String,
    val age: Int,
    val imageUrl: String,
    val fields: List<Field>,
    val codeUrl: String,
    val bio: String
)

@Suppress("LongParameterList")
fun userOf(
    name: String,
    age: Int,
    image: String = "",
    fields: List<Field>,
    codeUrl: String,
    bio: String
) =
    User(
        id = UUID.randomUUID().toString(),
        name = name,
        age = age,
        imageUrl = image,
        fields = fields,
        codeUrl = codeUrl,
        bio = bio
    )
