package com.donxux.codate.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.donxux.codate.data.db.ChatDatabase
import com.donxux.codate.data.source.ChatPagingSource
import com.donxux.codate.domain.model.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Suppress("UnusedPrivateProperty")
class ChatRepository @Inject constructor(
    private val database: ChatDatabase
) {

    fun getChatPagingData(): Flow<PagingData<Chat>> {
        return Pager(
            config = PagingConfig(pageSize = 2, enablePlaceholders = false),
            pagingSourceFactory = {
                ChatPagingSource()
            }
        ).flow
    }
}
