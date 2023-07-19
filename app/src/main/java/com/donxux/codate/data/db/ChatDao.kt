package com.donxux.codate.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.donxux.codate.domain.model.ChatRoom

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(chats: List<ChatRoom>)

    @Query("SELECT * FROM chats")
    fun getChats(): List<ChatRoom>
}
