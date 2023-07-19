package com.donxux.codate.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.donxux.codate.domain.model.ChatRoom
import com.donxux.codate.domain.model.Pagable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

@Suppress("TooGenericExceptionCaught")
class ChatRoomPagingSource : PagingSource<Int, ChatRoom>() {
    private val json = Json { ignoreUnknownKeys = true }

    override fun getRefreshKey(state: PagingState<Int, ChatRoom>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ChatRoom> {
        return try {
            val page = params.key ?: 1
            val response: Pagable = json.decodeFromString(testChatJson[page - 1])
            val data: List<ChatRoom> = json.decodeFromJsonElement(response.data)
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

private val testChatJson: List<String> = listOf(
    """
    {
        "data": [
            {
                "id": 1,
                "image": "https://picsum.photos/id/1/200",
                "name": "DONXUX",
                "recent_message": "자니? ... 요새 너 생각 많이 나길래 연락해봤어 요새 뭐하고 지내? 잘 지내고 있어? 나 너보려고 서울로 올라왔는데 한번만 만나주면안될까?",
                "message_count": 120,
                "date": "2023-06-28T19:13:40.00Z"
            },
            {
                "id": 2,
                "image": "https://picsum.photos/id/10/200",
                "name": "맹율",
                "recent_message": "커피챗 요청할게요! ><",
                "message_count": 24,
                "date": "2023-06-27T17:13:40.00Z"
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
                "image": "https://picsum.photos/id/20/200",
                "name": "제트",
                "recent_message": "ㅎㅇㅎㅇ",
                "message_count": 8,
                "date": "2023-05-27T17:13:40.00Z"
            },
            {
                "id": 4,
                "image": "https://picsum.photos/id/30/200",
                "name": "Kim Kadasian",
                "recent_message": "Hello?",
                "message_count": 5,
                "date": "2022-10-27T17:13:40.00Z"
            }
        ],
        "key": 2,
        "count": 2,
        "hasNext": true 
    }
    """.trimIndent(),
    """
    {
        "data": [
            {
                "id": 5,
                "image": "https://picsum.photos/id/40/200",
                "name": "김현수",
                "recent_message": "ㅎㅎㅎ",
                "message_count": 1,
                "date": "2023-05-27T17:13:40.00Z"
            },
            {
                "id": 6,
                "image": "https://picsum.photos/id/50/200",
                "name": "이주혁",
                "recent_message": "아 정말요?",
                "message_count": 0,
                "date": "2022-10-27T17:13:40.00Z"
            }
        ],
        "key": 3,
        "count": 2,
        "hasNext": true
    }
    """.trimIndent(),
    """
    {
        "data": [
            {
                "id": 7,
                "image": "https://picsum.photos/id/60/200",
                "name": "곽두팔",
                "recent_message": "아하!",
                "message_count": 0,
                "date": "2023-05-27T17:13:40.00Z"
            },
            {
                "id": 8,
                "image": "https://picsum.photos/id/70/200",
                "name": "김현지",
                "recent_message": "넵",
                "message_count": 0,
                "date": "2022-10-27T17:13:40.00Z"
            }
        ],
        "key": 4,
        "count": 2,
        "hasNext": true
    }
    """.trimIndent(),
    """
    {
        "data": [
            {
                "id": 9,
                "image": "https://picsum.photos/id/80/200",
                "name": "DONXUX",
                "recent_message": "조만간 연락드릴게요",
                "message_count": 0,
                "date": "2023-05-27T17:13:40.00Z"
            },
            {
                "id": 10,
                "image": "https://picsum.photos/id/90/200",
                "name": "주니어 개발자",
                "recent_message": "넹",
                "message_count": 0,
                "date": "2022-10-27T17:13:40.00Z"
            }
        ],
        "key": 5,
        "count": 2,
        "hasNext": true
    }
    """.trimIndent(),
    """
    {
        "data": [
            {
                "id": 11,
                "image": "https://picsum.photos/id/100/200",
                "name": "장원영",
                "recent_message": "ㅋㅋㅋㅋㅋㅋㅋㅋㅋ",
                "message_count": 0,
                "date": "2023-05-27T17:13:40.00Z"
            },
            {
                "id": 12,
                "image": "https://picsum.photos/id/110/200",
                "name": "개발고수",
                "recent_message": "아녀",
                "message_count": 0,
                "date": "2022-10-27T17:13:40.00Z"
            }
        ],
        "key": 6,
        "count": 2,
        "hasNext": true 
    }
    """.trimIndent(),
    """
    {
        "data": [
            {
                "id": 13,
                "image": "https://picsum.photos/id/120/200",
                "name": "이경영",
                "recent_message": "그 프로젝트 당장 진행시켜! 영!차!",
                "message_count": 0,
                "date": "2023-05-27T17:13:40.00Z"
            },
            {
                "id": 14,
                "image": "https://picsum.photos/id/130/200",
                "name": "인생은즐거워",
                "recent_message": "감사합니다~",
                "message_count": 0,
                "date": "2022-10-27T17:13:40.00Z"
            }
        ],
        "key": 7,
        "count": 2,
        "hasNext": false
    }
    """.trimIndent()
)
