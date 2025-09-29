package com.ihagoss.skipintro.utils

import android.app.ActivityManager
import android.content.Context

object AppDetectionUtils {
    fun isStremioRunning(context: Context): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningApps = activityManager.runningAppProcesses
        return runningApps.any { it.processName.contains("stremio", ignoreCase = true) }
    }
}