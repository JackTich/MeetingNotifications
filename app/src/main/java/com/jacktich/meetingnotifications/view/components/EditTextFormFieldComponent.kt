package com.jacktich.meetingnotifications.view.components

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.jacktich.meetingnotifications.R
import com.jacktich.meetingnotifications.utils.makeGone
import com.jacktich.meetingnotifications.utils.makeVisible
import com.jacktich.meetingnotifications.utils.validators.*
import com.redmadrobot.inputmask.MaskedTextChangedListener

class EditTextFormFieldComponent(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    private var etFormField: EditText
    private var imgRequired: TextView
    private var tvTitleForm: TextView
    private var tvErrorEtForm: TextView

    var validator: ValidatorsComposer<String>? = null

    init {
        View.inflate(context, R.layout.component_et_form_field, this)
        etFormField = findViewById(R.id.etFormMeeting)
        imgRequired = findViewById(R.id.imgIsEtFormRequired)
        tvTitleForm = findViewById(R.id.tvTitleEtForm)
        tvErrorEtForm = findViewById(R.id.tvErrorEtForm)

        val attributes =
            context.obtainStyledAttributes(attributeSet, R.styleable.EditTextFormFieldComponent)

        if (!attributes.getBoolean(R.styleable.EditTextFormFieldComponent_isFocusable, true)){
            etFormField.apply{
                isFocusable = false
                isClickable = true
            }
        }

        val isRequired = attributes.getBoolean(R.styleable.EditTextFormFieldComponent_isRequired, false)

        if (isRequired) {
            imgRequired.makeVisible()
        }

        tvTitleForm.text = attributes.getString(R.styleable.EditTextFormFieldComponent_textHeader)

        when (attributes.getString(R.styleable.EditTextFormFieldComponent_validation)) {
            "date" -> {
                composeValidatorsWithRequiredField(isRequired, DateValidator())
//                etFormField.inputType = InputType.TYPE_CLASS_NUMBER
                val listener = MaskedTextChangedListener(
                    "[00]{.}[00]{.}[9900]",
                    etFormField
                )
                etFormField.addTextChangedListener(listener)
            }
            "time" -> {
                composeValidatorsWithRequiredField(isRequired, TimeValidator())
            }
            else -> {
                if (isRequired){
                    composeValidatorsWithRequiredField(isRequired)
                }
            }
        }
        attributes.recycle()
    }

    private fun composeValidatorsWithRequiredField(isRequired: Boolean, vararg  validators: BaseValidator<String>){
        validator = if (isRequired) ValidatorsComposer(*validators, EmptyValidator()) else ValidatorsComposer(*validators)
    }

    fun showErrorMessage() {
        validator?.let {
            tvErrorEtForm.apply {
                text = context.getString(it.errorMessage)
                makeVisible()
            }
        }
    }

    fun hideErrorMessage(){
        tvErrorEtForm.apply {
            text = null
            makeGone()
        }
    }

}