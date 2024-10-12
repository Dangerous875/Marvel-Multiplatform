package com.klyxdevs.kmptp2024.data.network.service

import com.klyxdevs.kmptp2024.data.core.PUBLIC_KEY
import com.klyxdevs.kmptp2024.data.network.model.CharactersResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class APIService(private val httpClient: HttpClient) {
    suspend fun getAllCharacters(timestamp: Long, md5: String): CharactersResponse {
        val url = "/v1/public/characters?ts=$timestamp&hash=$md5&apikey=$PUBLIC_KEY"
        return httpClient.get(url).body()
    }
}