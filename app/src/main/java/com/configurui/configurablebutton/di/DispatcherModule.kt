package com.configurui.configurablebutton.di

import com.configurui.DefaultDispatcherProvider
import com.configurui.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Ramprasad on 5/7/26.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {
    @Binds
    abstract fun bindDispatcherProvider(impl: DefaultDispatcherProvider): DispatcherProvider
}
