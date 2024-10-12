package com.klyxdevs.kmptp2024.ui.mainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.klyxdevs.kmptp2024.data.network.model.CharacterResult
import com.klyxdevs.kmptp2024.data.network.model.CharactersResponse
import com.klyxdevs.kmptp2024.data.network.service.NetworkUtils.httpClient
import com.klyxdevs.kmptp2024.tpBase.PRIVATE_KEY
import com.klyxdevs.kmptp2024.tpBase.PUBLIC_KEY
import com.soywiz.krypto.MD5
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.util.date.getTimeMillis
import io.ktor.utils.io.core.toByteArray
import kmptp2024.composeapp.generated.resources.Res
import kmptp2024.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainScreen(navController: NavController){
    var superheroName by remember { mutableStateOf("") }
    var superheroList by remember { mutableStateOf<List<CharacterResult>>(emptyList()) }
    var url by remember { mutableStateOf("empty") }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Row(Modifier.fillMaxWidth()) {
                TextField(value = superheroName, onValueChange = { superheroName = it })
                Button(onClick = { getSuperheroList(superheroName, { superheroList = it }, { url = it }) }) {
                    Text("Load")
                }
            }

            //List
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(superheroList) { hero ->
                    val urlHero = "${hero.thumbnail.path}.${hero.thumbnail.extension}"
                    Text(hero.name)
                    Text(urlHero)
                    AsyncImage(
                        model = urlHero,
                        contentDescription = null,
                        error = painterResource(Res.drawable.compose_multiplatform)
                    )
                }
            }
        }

    }
}


fun getSuperheroList(
    superheroName: String,
    onSuccessResponse: (List<CharacterResult>) -> Unit,
    getURL: (String) -> Unit
) {
    val timestamp = getTimeMillis()
    val md5 = md5Refactor(timestamp.toString() + PRIVATE_KEY + PUBLIC_KEY)
    val url =
        "https://gateway.marvel.com/v1/public/characters?ts=$timestamp&hash=$md5&apikey=$PUBLIC_KEY"
    getURL(url)
    CoroutineScope(Dispatchers.Unconfined).launch {

        val response = httpClient.get(url).body<CharactersResponse>()
        onSuccessResponse(response.characters.list)

    }
}

private fun md5Refactor(string: String): String {
    val messageDigest = MD5.digest(string.toByteArray())
    return messageDigest.hex
}