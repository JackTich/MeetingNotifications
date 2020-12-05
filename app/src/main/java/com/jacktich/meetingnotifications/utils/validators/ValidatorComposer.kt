package com.jacktich.meetingnotifications.utils.validators

class ValidatorsComposer<T>(vararg validators: BaseValidator<T>) :
    BaseValidator<T> {

    private val validatorsList: List<BaseValidator<T>> = validators.toList().sortedBy { it is EmptyValidator }

    override fun isValid(value: T): Boolean {
        for (validator in validatorsList) {
            if (!validator.isValid(value)) {
                errorMessage = validator.errorMessage
                return false
            }
        }
        return true
    }

    override var errorMessage: Int = - 1


}