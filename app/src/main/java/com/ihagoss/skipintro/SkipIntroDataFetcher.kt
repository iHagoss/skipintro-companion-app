package com.ihagoss.skipintro

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

object SkipIntroDataFetcher {
    // Use the same API as your skip intro Stremio Enhanced addon
    private const val SERVER_URL = "https://busy-jacinta-shugi-c2885b2e.koyeb.app"

    suspend fun getIntroRange(episodeId: String, fileId: String, title: String): IntroRange? {
        return withContext(Dispatchers.IO) {
            val params = "fileId=$fileId&title=$title"
            val url = "$SERVER_URL/ranges/${episodeId}?$params"
            try {
                val response = URL(url).readText()
                val json = JSONObject(response)
                val start = json.optDouble("start", 0.0)
                val end = json.optDouble("end", 0.0)
                val offset = json.optDouble("offset", 0.0)
                IntroRange(start, end, offset)
            } catch (e: Exception) {
                null
            }
        }
    }
}

data class IntroRange(val start: Double, val end: Double, val offset: Double)
