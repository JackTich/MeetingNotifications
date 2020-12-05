package com.jacktich.meetingnotifications.utils.validators

import com.jacktich.meetingnotifications.R
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.lang.Exception
import java.util.*

class DateValidator(): BaseValidator<String> {

    override fun isValid(value: String): Boolean {

        if (value.isEmpty()){
            return true
        }

        val parser = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.US)

        return try {
            val date = LocalDate.parse(value, parser)
            if (LocalDate.now().atStartOfDay() > date.atStartOfDay()){
                errorMessage = R.string.error_last_date
                return false
            }
            true
        }catch (e: Exception){
            errorMessage = R.string.error_parsing_date
            false
        }
    }

    override var errorMessage: Int = -1


}