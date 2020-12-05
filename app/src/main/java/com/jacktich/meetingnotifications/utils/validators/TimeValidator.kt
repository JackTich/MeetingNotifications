package com.jacktich.meetingnotifications.utils.validators

import com.jacktich.meetingnotifications.R
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.lang.Exception
import java.util.*

class TimeValidator(): BaseValidator<String> {
    companion object{
        const val SEPARATE_DATE_SYMBOL = " "
    }

    override fun isValid(value: String): Boolean {

        if (value.isEmpty()  || value == SEPARATE_DATE_SYMBOL){
            return true
        }

        val parser = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.US)

        return try {
            val date = LocalDate.parse(value, parser)
            val today = LocalDate.now()
            if (today.atStartOfDay() == date.atStartOfDay() && today > date){
                errorMessage = R.string.error_last_time
                return false
            }
            true
        }catch (e: Exception){
            errorMessage = R.string.error_empty_date
            false
        }
    }

    override var errorMessage: Int = -1
}