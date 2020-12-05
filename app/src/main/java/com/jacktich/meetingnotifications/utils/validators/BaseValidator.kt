package com.jacktich.meetingnotifications.utils.validators

interface BaseValidator<T> {
    fun isValid(value: T): Boolean
    var errorMessage: Int
}