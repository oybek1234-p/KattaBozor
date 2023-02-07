package com.uz.kattabozor.data

import com.uz.kattabozor.data.models.PostResponse
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class Repository {
    var ktor = HttpClient(CIO){
        install(Logging)
    }
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    companion object {
        const val baseUrl = "https://www.kattabozor.uz/hh/test/api/v1/"
    }

    private fun buildUrl(url: String): String {
        val builder = StringBuilder()
        builder.append(baseUrl)
        builder.append(url)
        return builder.toString()
    }

    fun getPosts(callback: (list: PostResponse?, error: String?) -> Unit) {
        scope.launch {
            try {
                val response = ktor.get(buildUrl("offers"))
                val text = response.bodyAsText()
                val postsResponse = Json.decodeFromString(PostResponse.serializer(), text)
                launch(Dispatchers.Main) {
                    callback.invoke(postsResponse, null)
                }
            } catch (e: java.lang.Exception) {
                launch(Dispatchers.Main) {
                    callback.invoke(null, e.message)
                }
            }
        }
    }

    fun test() {
        buildUrl("url")
    }
}