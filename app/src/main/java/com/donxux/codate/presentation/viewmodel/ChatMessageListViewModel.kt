package com.donxux.codate.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.paging.cachedIn
import com.donxux.codate.data.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject

@HiltViewModel
class ChatMessageListViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    init {
        getChatWebSocket()
    }

    companion object {
        val TAG: String = ChatMessageListViewModel::class.java.simpleName
    }

    private fun getChatWebSocket() {
        val client = OkHttpClient()
        val request = Request.Builder().url("ws://10.0.2.2:8080/chat").build()

        // TODO: Implement send and receive
        val webSocket = client.newWebSocket(
            request,
            object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) {
                    webSocket.send("Open WebSocket")
                }

                override fun onMessage(webSocket: WebSocket, text: String) {
                    Log.e(TAG, "message : $text")
                }

                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                    Log.e(TAG, "code : $code (reason : $reason)")
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    Log.e(TAG, "$response (Throws : $t)")
                }
            }
        )
    }

    fun getChatMessagesPagingData() = chatRepository.getChatMessagesPagingData().cachedIn(
        CoroutineScope(Dispatchers.IO)
    )
}
