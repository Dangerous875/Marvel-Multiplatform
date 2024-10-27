package com.klyxdevs.kmptp2024.domain.usecases

import app.cash.sqldelight.db.SqlDriver
import com.klyxdevs.kmptp2024.SuperHeroDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.koin.mp.KoinPlatform.getKoin

data class Test(val id : Long , val name : String)

class GetTestDB {

    private val sqlDriver: SqlDriver = getKoin().get()
    private val database = SuperHeroDB(sqlDriver)
    private val exampleEntityQueries = database.superHeroDBQueries

    suspend operator fun invoke(): List<Test> = withContext(Dispatchers.IO) {
        // Ejecuta la consulta y mapea el resultado
        exampleEntityQueries.getAll()
            .executeAsList()
            .map { row -> Test(id = row.id, name = row.name) }
    }
}