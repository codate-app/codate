package com.donxux.codate.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.donxux.codate.domain.model.Chat

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(chats: List<Chat>)

    @Query("SELECT * FROM chats")
    fun getChats(): List<Chat>

    @Query("DELETE FROM chats")
    suspend fun clearRepos()
}
