package com.donxux.codate.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.donxux.codate.utils.ZonedDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

@Serializable
@Entity(tableName = "chats")
data class ChatRoom(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @SerialName("name")
    @ColumnInfo("name")
    val name: String,

    @SerialName("recent_message")
    @ColumnInfo("recent_message")
    val recentMessage: String,

    @SerialName("image")
    @ColumnInfo("image")
    val image: String,

    @SerialName("message_count")
    @ColumnInfo("message_count")
    val messageCount: Int,

    @Serializable(with = ZonedDateTimeSerializer::class)
    @SerialName("date")
    @ColumnInfo("date")
    val date: ZonedDateTime
)
