package com.donxux.codate.domain.model

import java.util.UUID

data class User(val id: String, val name: String, val age: Int, val fields: List<Field>, val bio: String)

fun userOf(name: String, age: Int, fields: List<Field>, bio: String) =
    User(id = UUID.randomUUID().toString(), name = name, age = age, fields = fields, bio = bio)
