package com.klyxdevs.kmptp2024.data.di

import com.klyxdevs.kmptp2024.data.database.CharactersProvider
import com.klyxdevs.kmptp2024.data.network.service.APIService
import com.klyxdevs.kmptp2024.data.repository.RepositoryProvider
import com.klyxdevs.kmptp2024.domain.repository.Repository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "gateway.marvel.com"
                }
            }
        }
    }

    factoryOf(::APIService)
    factory<Repository> { RepositoryProvider(get(),get()) }
    singleOf(::CharactersProvider)
}