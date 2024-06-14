package com.configurui.configurablebutton.di

import android.content.Context
import com.configurui.configurablebutton.JsonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ramprasad on 6/14/24.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJsonRepository(@ApplicationContext context: Context): JsonRepository {
        return JsonRepository(context)
    }
}
