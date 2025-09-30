package com.ihagoss.skipintro

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class AccessibilityController : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Only needed for active service, direct call used for skipTo below
    }

    override fun onInterrupt() {}

    companion object {
        fun skipTo(context: Context, seconds: Double) {
            // Simulate seeking in Stremio's player using Accessibility API
            // Find the seek bar and perform ACTION_CLICK at the correct position
            val service = context as? AccessibilityController ?: return

            val rootNode: AccessibilityNodeInfo? = service.rootInActiveWindow
            if (rootNode != null) {
                val seekNodes = ArrayList<AccessibilityNodeInfo>()
                findSeekBarNodes(rootNode, seekNodes)
                for (node in seekNodes) {
                    // You may need to adjust this logic for Stremio's seek bar
                    node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                }
            }
        }

        private fun findSeekBarNodes(node: AccessibilityNodeInfo, results: MutableList<AccessibilityNodeInfo>) {
            if (node.className == "android.widget.SeekBar") {
                results.add(node)
            }
            for (i in 0 until node.childCount) {
                node.getChild(i)?.let { findSeekBarNodes(it, results) }
            }
        }
    }
}
