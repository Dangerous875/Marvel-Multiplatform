package com.klyxdevs.kmptp2024.ui.mainScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.klyxdevs.kmptp2024.data.network.model.CharacterResult

@Composable
fun MainScreen(navController: NavController){


//    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally){
//            Row(Modifier.fillMaxWidth()) {
//                TextField(value = superheroName, onValueChange = { superheroName = it })
//                Button(onClick = { getSuperheroList(superheroName, { superheroList = it }, { url = it }) }) {
//                    Text("Load")
//                }
//            }
//
//            //List
//            LazyColumn(modifier = Modifier.fillMaxWidth()) {
//                items(superheroList) { hero ->
//                    val urlHero = "${hero.thumbnail.path}.${hero.thumbnail.extension}"
//                    Text(hero.name)
//                    Text(urlHero)
//                    AsyncImage(
//                        model = urlHero,
//                        contentDescription = null,
//                        error = painterResource(Res.drawable.compose_multiplatform)
//                    )
//                }
//            }
//        }
//
//    }
}


//fun getSuperheroList(
//    superheroName: String,
//    onSuccessResponse: (List<CharacterResult>) -> Unit,
//    getURL: (String) -> Unit
//) {
//    val timestamp = getTimeMillis()
//    val md5 = md5Refactor(timestamp.toString() + PRIVATE_KEY + PUBLIC_KEY)
//    val url =
//        "https://gateway.marvel.com/v1/public/characters?ts=$timestamp&hash=$md5&apikey=$PUBLIC_KEY"
//    getURL(url)
//    CoroutineScope(Dispatchers.Unconfined).launch {
//
//        val response = httpClient.get(url).body<CharactersResponse>()
//        onSuccessResponse(response.characters.list)
//
//    }
//}
//
//private fun md5Refactor(string: String): String {
//    val messageDigest = MD5.digest(string.toByteArray())
//    return messageDigest.hex
//}