package com.klyxdevs.kmptp2024.tpBase

import com.klyxdevs.kmptp2024.data.network.model.CharactersResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MarvelCharactersClient(private val httpClient: HttpClient) {

    suspend fun getAllCharacters(timestamp: Long, md5: String): CharactersResponse {
        val url = "https://gateway.marvel.com/v1/public/characters?ts=$timestamp&hash=$md5&apikey=$PUBLIC_KEY"
        return httpClient.get(url).body()
    }
}