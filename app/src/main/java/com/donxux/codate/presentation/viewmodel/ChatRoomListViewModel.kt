package com.donxux.codate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.cachedIn
import com.donxux.codate.data.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ChatRoomListViewModel @Inject constructor(private val chatRepository: ChatRepository) :
    ViewModel() {
    fun getChatRoomsPagingData() = chatRepository.getChatRoomsPagingData().cachedIn(
        CoroutineScope(Dispatchers.IO)
    )
}
