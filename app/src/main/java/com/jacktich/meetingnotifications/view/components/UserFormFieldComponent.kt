package com.jacktich.meetingnotifications.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.jacktich.meetingnotifications.R
import com.jacktich.meetingnotifications.utils.makeGone
import com.jacktich.meetingnotifications.utils.makeVisible
import com.jacktich.meetingnotifications.view.chooseclient.models.UserModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_create_meeting.*

class UserFormFieldComponent(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    private var imgClientFormUser: ImageView
    private var tvPeopleNameFormUser: TextView
    private var tvEmailFormUser: TextView
    private var tvErrorUserForm: TextView

    private var isInit: Boolean = false

    init {
        View.inflate(context, R.layout.component_user_form_field, this)
        imgClientFormUser = findViewById(R.id.imgClientFormUser)
        tvPeopleNameFormUser = findViewById(R.id.tvPeopleNameFormUser)
        tvEmailFormUser = findViewById(R.id.tvEmailFormUser)
        tvErrorUserForm = findViewById(R.id.tvErrorUserForm)
    }

    fun isUserInit(): Boolean{
        return if (isInit){
            tvErrorUserForm.makeGone()
            true
        }else{
            tvErrorUserForm.makeVisible()
            false
        }
    }

    fun createUserForm(user: UserModel){
        Picasso.get()
            .load(user.img)
            .error(ContextCompat.getDrawable(context, R.drawable.ic_error)!!)
            .fit()
            .centerCrop()
            .into(imgClientFormUser)
        tvPeopleNameFormUser.text = user.name
        tvEmailFormUser.text = user.email
        isInit = true
    }

}