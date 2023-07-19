package com.donxux.codate.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.donxux.codate.domain.model.ChatMessage
import com.donxux.codate.domain.model.Pagable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

@Suppress("TooGenericExceptionCaught")
class ChatMessagePagingSource : PagingSource<Int, ChatMessage>() {
    private val json = Json { ignoreUnknownKeys = true }

    override fun getRefreshKey(state: PagingState<Int, ChatMessage>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ChatMessage> {
        return try {
            val page = params.key ?: 1
            val response: Pagable = json.decodeFromString(testChatMessageJson[page - 1])
            val data: List<ChatMessage> = json.decodeFromJsonElement(response.data)
            val nextKey = if (response.hasNext.not()) {
                null
            } else {
                page + 1
            }
            LoadResult.Page(
                data = data,
                prevKey = if (page > 1) page else null, // Only paging forward.
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

private val testChatMessageJson = listOf(
    """
    {
        "data": [
            {
                "id": 1,
                "message": "자니? ... 요새 너 생각 많이 나길래 연락해봤어 요새 뭐하고 지내? 잘 지내고 있어? 나 너보려고 서울로 올라왔는데 한번만 만나주면안될까?",
                "date": "2023-06-28T19:13:40.00Z",
                "is_sender": false  
            },
            {
                "id": 2,
                "message": "아니",
                "date": "2023-06-28T19:13:40.00Z",
                "is_sender": true
            }
        ],
        "key": 1,
        "count": 2,
        "hasNext": true
    }
    """.trimIndent(),
    """
    {
        "data": [
            {
                "id": 3,
                "message": "자니? ... 요새 너 생각 많이 나길래 연락해봤어 요새 뭐하고 지내? 잘 지내고 있어? 나 너보려고 서울로 올라왔는데 한번만 만나주면안될까?",
                "date": "2023-06-28T19:13:40.00Z",
                "is_sender": false  
            },
            {
                "id": 4,
                "message": "아니",
                "date": "2023-06-28T19:13:40.00Z",
                "is_sender": true
            }
        ],
        "key": 1,
        "count": 2,
        "hasNext": true
    }
    """.trimIndent()
)
