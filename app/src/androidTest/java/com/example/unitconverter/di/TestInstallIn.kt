package com.example.unitconverter.di

import com.example.unitconverter.domain.ConverterEngine
import com.example.unitconverter.domain.DefaultConverterEngine
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

/**
 * Test version of the ConverterModule.
 * It replaces the production one during androidTest runs.
 */
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ConverterModule::class]
)
abstract class TestInstallIn {

    @Binds
    @Singleton
    abstract fun bindConverterEngine(impl: DefaultConverterEngine): ConverterEngine
}
