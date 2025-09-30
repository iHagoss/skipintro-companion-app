package com.ihagoss.skipintro

import android.content.Context
import android.app.usage.UsageStatsManager
import android.app.usage.UsageEvents
import java.util.*

object StremioInfoDetector {
    data class EpisodeInfo(val episodeId: String, val fileId: String, val title: String)

    // This is a best-effort guess using usage events and window title (customize as needed)
    fun getCurrentEpisodeInfo(context: Context): EpisodeInfo? {
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val now = System.currentTimeMillis()
        val usageEvents = usageStatsManager.queryEvents(now - 1000 * 60 * 5, now)
        var lastTitle: String? = null
        while (usageEvents.hasNextEvent()) {
            val event = UsageEvents.Event()
            usageEvents.getNextEvent(event)
            if (event.packageName == "com.stremio.stremio" && event.eventType == UsageEvents.Event.ACTIVITY_RESUMED) {
                lastTitle = event.className
            }
        }
        // Replace below with how Stremio Enhanced gets fileId/episodeId
        val fileId = lastTitle ?: "unknown_file"
        val episodeId = lastTitle ?: "unknown_episode"
        val title = lastTitle ?: "unknown_title"
        return EpisodeInfo(episodeId, fileId, title)
    }
}
