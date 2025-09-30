package com.ihagoss.skipintro

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var statusText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.status_text)

        val overlayButton: Button = findViewById(R.id.enable_overlay)
        overlayButton.setOnClickListener {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
            startActivity(intent)
        }

        val startServiceButton: Button = findViewById(R.id.start_overlay)
        startServiceButton.setOnClickListener {
            startService(Intent(this, OverlayService::class.java))
            statusText.text = "Overlay status: Running"
        }
    }
}
