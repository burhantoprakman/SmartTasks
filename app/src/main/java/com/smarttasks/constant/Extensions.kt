package com.smarttasks.constant

object Extensions {
    /**
     * Extension function to return "NA" if the string is null or empty.
     */
    fun String?.orNA(): String {
        return if (this.isNullOrEmpty()) "NA" else this
    }
}