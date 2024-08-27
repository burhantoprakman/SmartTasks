package com.smarttasks.utils

import android.content.Context
import android.content.SharedPreferences

object TaskSharedPreference {
    private const val PREF_NAME = "task_preferences"
    private const val KEY_IS_RESOLVED = "key_is_resolved"
    private const val KEY_COMMENT = "key_comment"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveIsResolved(context: Context, taskId: String, isResolved: Boolean) {
        val editor = getPreferences(context).edit()
        editor.putBoolean(KEY_IS_RESOLVED + taskId, isResolved)
        editor.apply()
    }

    fun getIsResolved(context: Context, taskId: String): Boolean? {
        val prefs = getPreferences(context)
        return if (prefs.contains(KEY_IS_RESOLVED + taskId)) {
            prefs.getBoolean(KEY_IS_RESOLVED + taskId, false)
        } else {
            null
        }
    }

    fun saveComment(context: Context, taskId: String, comment: String) {
        val editor = getPreferences(context).edit()
        editor.putString(KEY_COMMENT + taskId, "Comment: $comment")
        editor.apply()
    }

    fun getComment(context: Context, taskId: String): String? {
        return getPreferences(context).getString(KEY_COMMENT + taskId, "")
    }
}