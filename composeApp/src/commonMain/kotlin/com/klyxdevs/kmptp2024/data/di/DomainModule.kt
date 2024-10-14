package com.klyxdevs.kmptp2024.data.di

import com.klyxdevs.kmptp2024.domain.usecases.GetCharacterByIDUseCase
import com.klyxdevs.kmptp2024.domain.usecases.GetCharactersFromApiUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetCharactersFromApiUseCase)
    factoryOf(::GetCharacterByIDUseCase)
}