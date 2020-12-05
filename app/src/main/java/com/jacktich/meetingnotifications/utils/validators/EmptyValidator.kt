package com.jacktich.meetingnotifications.utils.validators

import com.jacktich.meetingnotifications.R

class EmptyValidator(): BaseValidator<String> {

    override fun isValid(value: String): Boolean = value.isNotEmpty()


    override var errorMessage: Int = R.string.error_empty

}