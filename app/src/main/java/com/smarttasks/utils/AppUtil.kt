package com.smarttasks.utils

import com.smarttasks.model.Task
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object AppUtil {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    /**
     * Calculates the number of days between two dates.
     *
     * @param startDateString The start date as a string.
     * @param endDateString The end date as a string.
     * @return The number of days between the two dates as a string.
     */
    fun calculateDaysBetween(startDateString: String, endDateString: String): String {
        return try {
            val startDate = LocalDate.parse(startDateString, formatter)
            val endDate = LocalDate.parse(endDateString, formatter)

            ChronoUnit.DAYS.between(startDate, endDate).toString()
        } catch (e: Exception) {
            "NA"
        }

    }

    /**
     * Filters a list of tasks by a specific date.
     *
     * @param taskList The list of tasks to filter.
     * @param currentDate The date to filter tasks by.
     * @return A list of tasks that match the given date.
     */
    fun filterByDate(taskList: List<Task>, currentDate: LocalDate): List<Task> {
        val filteredTask = taskList.filter { task ->
            task.TargetDate?.let {
                LocalDate.parse(it, formatter) == currentDate
            } ?: false
        }
        return filteredTask
    }

    // Formats a LocalDate into a string.
    fun dateFormat(date: LocalDate): String {
        return date.format(formatter)
    }

    /**
     * Checks if a given date is today.
     *
     * @param date The date to check.
     * @return True if the date is today, false otherwise.
     */
    fun isToday(date: LocalDate): Boolean {
        return date == LocalDate.now()
    }
}