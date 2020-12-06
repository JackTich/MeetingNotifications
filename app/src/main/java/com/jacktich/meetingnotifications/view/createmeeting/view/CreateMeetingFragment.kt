package com.jacktich.meetingnotifications.view.createmeeting.view

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.isNotEmpty
import com.jacktich.meetingnotifications.R
import com.jacktich.meetingnotifications.data.database.MeetingEntity
import com.jacktich.meetingnotifications.utils.navFragment
import com.jacktich.meetingnotifications.view.base.view.BaseFragment
import com.jacktich.meetingnotifications.view.chooseclient.models.UserModel
import com.jacktich.meetingnotifications.view.chooseclient.view.ChooseClientFragment
import com.jacktich.meetingnotifications.view.createmeeting.interactor.CreateMeetingMVPInteractor
import com.jacktich.meetingnotifications.view.createmeeting.models.CreateUserFieldsModel
import com.jacktich.meetingnotifications.view.createmeeting.presenter.CreateMeetingMVPPresenter
import kotlinx.android.synthetic.main.activity_meetings.*
import kotlinx.android.synthetic.main.component_et_form_field.view.*
import kotlinx.android.synthetic.main.fragment_create_meeting.*
import org.threeten.bp.LocalTime
import javax.inject.Inject

class CreateMeetingFragment : BaseFragment(), CreateMeetingMVPView {

    companion object {
        const val KEY_ET_FIELDS = "keyEtFields"
    }

    @Inject
    lateinit var presenter: CreateMeetingMVPPresenter<CreateMeetingMVPView, CreateMeetingMVPInteractor>

    private lateinit var dialogTimePicker: TimePickerDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_meeting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach(this)
        initToolbar()
        initFragmentWithBundle()
        onClick()
    }

    fun initFragmentWithBundle() {
        arguments?.getParcelable<UserModel>(ChooseClientFragment.KEY_USER_BUNDLE)?.let { user ->
            userFormCreateMeeting.createUserForm(user)
        }

        arguments?.getParcelable<CreateUserFieldsModel>(KEY_ET_FIELDS)?.let {fields->
            fields.title?.let{
                titleEtForm.etFormMeeting.setText(it)
            }
            fields.date?.let{
                dateEtForm.etFormMeeting.setText(it)
            }
            fields.time?.let{
                timeEtForm.etFormMeeting.setText(it)
            }
        }
    }

    private fun onClick() {
        timeEtForm.etFormMeeting.setOnClickListener {
            if (!this::dialogTimePicker.isInitialized) {
                initTimePickerDialog()
            }
            dialogTimePicker.show()
        }
        btnAddMeeting.setOnClickListener {
            createMeeting()
        }
        userFormCreateMeeting.setOnClickListener {
            openChooseUserFragment()
        }
        val onBackPressedCallback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navFragment(R.id.meetings_list_navigation)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
    }

    private fun openChooseUserFragment() {
        val fieldsModel = CreateUserFieldsModel()

        if(titleEtForm.etFormMeeting.text.isNotEmpty()){
            fieldsModel.title = titleEtForm.etFormMeeting.text.toString()
        }
        if(dateEtForm.etFormMeeting.text.isNotEmpty()) {
            fieldsModel.date = dateEtForm.etFormMeeting.text.toString()
        }
        if(timeEtForm.etFormMeeting.text.isNotEmpty()) {
            fieldsModel.time = timeEtForm.etFormMeeting.text.toString()
        }
        if (arguments == null) {
            val bundle = Bundle()
            bundle.putParcelable(KEY_ET_FIELDS, fieldsModel)
            arguments = bundle
        }else{
            requireArguments().putParcelable(KEY_ET_FIELDS, fieldsModel)
        }
        navFragment(R.id.choose_client_navigation, arguments)
    }

    private fun initTimePickerDialog() {
        dialogTimePicker = TimePickerDialog(
            requireActivity(),
            { _, hourOfDay, minute ->
                val time = LocalTime.of(hourOfDay, minute)
                timeEtForm.etFormMeeting.setText(time.toString())
            },
            LocalTime.now().plusHours(1).hour,
            0,
            true
        )
    }

    private fun initToolbar() {
        requireActivity().tbMeetings.apply {
            if(menu.isNotEmpty()){
                menu.getItem(0).isVisible = false
            }
            title = context.getString(R.string.create_meeting)
            navigationIcon = ContextCompat.getDrawable(requireActivity(), R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                navFragment(R.id.meetings_list_navigation)
            }
        }
    }

    private fun createMeeting() {

        var isValid = true

        titleEtForm.validator?.let {
            if (it.isValid(titleEtForm.etFormMeeting.text.toString())) {
                titleEtForm.hideErrorMessage()
            } else {
                titleEtForm.showErrorMessage()
                isValid = false
            }
        }

        dateEtForm.validator?.let {
            if (it.isValid(dateEtForm.etFormMeeting.text.toString())) {
                dateEtForm.hideErrorMessage()
            } else {
                dateEtForm.showErrorMessage()
                isValid = false
            }
        }

        timeEtForm.validator?.let {
            if (it.isValid(dateEtForm.etFormMeeting.text.toString() + " " + timeEtForm.etFormMeeting.text.toString())) {
                timeEtForm.hideErrorMessage()
            } else {
                timeEtForm.showErrorMessage()
                isValid = false
            }
        }

        if (!userFormCreateMeeting.isUserInit()) {
            isValid = false
        }

        if (isValid) {
            val user =
                requireArguments().getParcelable<UserModel>(ChooseClientFragment.KEY_USER_BUNDLE)!!
            presenter.insertMeeting(
                MeetingEntity(
                    title = titleEtForm.etFormMeeting.text.toString(),
                    time = timeEtForm.etFormMeeting.text.toString(),
                    date = dateEtForm.etFormMeeting.text.toString(),
                    email = user.email,
                    userName = user.name,
                    img = user.img
                )
            )
        }

    }

    override fun returnToListSuccess() {
        navFragment(R.id.meetings_list_navigation)
    }

    override fun showDBMessage() {
        Toast.makeText(activity, getString(R.string.error_db), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}