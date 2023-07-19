package com.donxux.codate.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.donxux.codate.data.db.ChatDatabase
import com.donxux.codate.data.source.ChatMessagePagingSource
import com.donxux.codate.data.source.ChatRoomPagingSource
import com.donxux.codate.domain.model.ChatMessage
import com.donxux.codate.domain.model.ChatRoom
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Suppress("UnusedPrivateProperty")
class ChatRepository @Inject constructor(
    private val database: ChatDatabase
) {

    fun getChatRoomsPagingData(): Flow<PagingData<ChatRoom>> {
        return Pager(
            config = PagingConfig(pageSize = 2, enablePlaceholders = false),
            pagingSourceFactory = {
                ChatRoomPagingSource()
            }
        ).flow
    }

    fun getChatMessagesPagingData(): Flow<PagingData<ChatMessage>> {
        return Pager(
            config = PagingConfig(pageSize = 2, enablePlaceholders = false),
            pagingSourceFactory = {
                ChatMessagePagingSource()
            }
        ).flow
    }
}
