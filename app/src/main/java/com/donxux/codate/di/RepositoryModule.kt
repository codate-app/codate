package com.donxux.codate.di

import android.content.Context
import com.donxux.codate.data.db.ChatDatabase
import com.donxux.codate.data.repository.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideChatRepository(@ApplicationContext context: Context): ChatRepository =
        ChatRepository(ChatDatabase.getInstance(context))
}
