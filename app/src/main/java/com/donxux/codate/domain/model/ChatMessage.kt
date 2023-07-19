package com.donxux.codate.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.donxux.codate.utils.ZonedDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

@Serializable
data class ChatMessage(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @SerialName("message")
    @ColumnInfo("message")
    val message: String,

    @Serializable(with = ZonedDateTimeSerializer::class)
    @SerialName("date")
    @ColumnInfo("date")
    val date: ZonedDateTime,

    @SerialName("is_sender")
    @ColumnInfo("is_sender")
    val isSender: Boolean
)
