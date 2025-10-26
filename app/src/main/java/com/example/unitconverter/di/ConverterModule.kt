package com.example.unitconverter.di

import com.example.unitconverter.domain.ConverterEngine
import com.example.unitconverter.domain.DefaultConverterEngine
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConverterModule {
    @Binds
    @Singleton
    abstract fun bindConverterEngine(impl: DefaultConverterEngine): ConverterEngine
}
