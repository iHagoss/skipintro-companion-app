package com.ihagoss.skipintrocompanionapp

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.ImageButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OverlayService : Service() {
    private lateinit var windowManager: WindowManager
    private var overlayView: ImageButton? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.overlay_button, null)
        overlayView = view.findViewById(R.id.skip_button)

        overlayView?.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val episodeInfo = StremioInfoDetector.getCurrentEpisodeInfo(this@OverlayService)
                if (episodeInfo != null) {
                    val range = SkipIntroDataFetcher.getIntroRange(episodeInfo.episodeId, episodeInfo.fileId, episodeInfo.title)
                    if (range != null && range.start > 0) {
                        AccessibilityController.skipTo(this@OverlayService, range.start + range.offset)
                    }
                }
            }
        }

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )
        windowManager.addView(view, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        overlayView?.let { windowManager.removeView(it) }
    }
}
