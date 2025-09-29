package com.ihagoss.skipintro

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class AccessibilityController : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // TODO: Implement logic to detect Stremio player and simulate seek to skip intro
    }

    override fun onInterrupt() {
        // Required method
    }
}