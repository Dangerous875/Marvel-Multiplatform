package com.klyxdevs.kmptp2024.data.repository

import co.touchlab.kermit.Logger
import com.klyxdevs.kmptp2024.DatabaseDriverFactory
import com.klyxdevs.kmptp2024.SuperHeroDB
import com.klyxdevs.kmptp2024.data.database.CharactersProvider
import com.klyxdevs.kmptp2024.data.local.Character
import com.klyxdevs.kmptp2024.data.network.model.CharactersResponse
import com.klyxdevs.kmptp2024.data.network.service.APIService
import com.klyxdevs.kmptp2024.domain.model.CharacterDomain
import com.klyxdevs.kmptp2024.domain.model.toDataBase
import com.klyxdevs.kmptp2024.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class RepositoryProvider(
    private val api: APIService,
    private val characterProvider: CharactersProvider,
    databaseDriverFactory: DatabaseDriverFactory
) : Repository {

    private val db = SuperHeroDB(databaseDriverFactory.createDriver())
    private val queryInDB = db.superHeroDBQueries

    override suspend fun getCharacters(timestamp: Long, md5: String): List<Character> {
        return api.getAllCharacters(timestamp, md5).toModel()
    }

    override fun setCharactersDataBase(list: List<Character>) {
        characterProvider.listCharacters = list
    }

    override fun getCharactersDataBase(): List<Character> {
        return characterProvider.listCharacters
    }

    override fun getCharactersByIdDataBase(id: Long): Character {
        val characterSelected = characterProvider.listCharacters.find { it.id == id }
        return characterSelected!!
    }

    override suspend fun insertSuperHeroesSQLDelight(list: List<CharacterDomain>) {
        setCharactersDataBase(list.map { it.toDataBase() })
        withContext(Dispatchers.IO) {
            queryInDB.transaction {
                for (hero in list) {
                    queryInDB.insertCharacter(
                        id = hero.id,
                        name = hero.name,
                        description = hero.description,
                        thumbnailUrl = hero.imageURL
                    )
                    Logger.i { "Inyeccion ->  $hero" }
                }
            }
        }
    }

    override suspend fun getSuperHeroesSQLDelight(): List<CharacterDomain> {
        return queryInDB.getAllCharacters { id: Long, name: String, description: String, thumbnailUrl: String ->
            CharacterDomain(id, name, description, thumbnailUrl)
        }.executeAsList()
    }

    override suspend fun deleteSuperHeroesSQLDelight() {
        queryInDB.deleteAllCharacters()
    }
}


private fun CharactersResponse.toModel(): List<Character> {
    return characters.list.map {
        Character(
            id = it.id,
            name = it.name,
            description = it.description,
            thumbnailUrl = it.thumbnail
        )
    }
}


