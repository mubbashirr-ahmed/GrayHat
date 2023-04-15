package com.assesment.grayhat.data

import com.assesment.grayhat.data.repository.IRepository
import com.assesment.grayhat.data.repository.RepositoryImp
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideItemsRepository(database: FirebaseDatabase):IRepository{
        return RepositoryImp(database)
    }
}