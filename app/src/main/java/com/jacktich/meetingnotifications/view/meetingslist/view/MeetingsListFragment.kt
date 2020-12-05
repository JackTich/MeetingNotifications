package com.jacktich.meetingnotifications.view.meetingslist.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isNotEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import com.jacktich.meetingnotifications.R
import com.jacktich.meetingnotifications.data.database.MeetingEntity
import com.jacktich.meetingnotifications.utils.navFragment
import com.jacktich.meetingnotifications.utils.receivers.NotificationBroadcastReceiver
import com.jacktich.meetingnotifications.view.base.view.BaseFragment
import com.jacktich.meetingnotifications.view.meetingslist.adapters.MeetingsListAdapter
import com.jacktich.meetingnotifications.view.meetingslist.interactor.MeetingsListMVPInteractor
import com.jacktich.meetingnotifications.view.meetingslist.presenter.MeetingsListMVPPresenter
import kotlinx.android.synthetic.main.activity_meetings.*
import kotlinx.android.synthetic.main.fragment_meetings_list.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MeetingsListFragment : BaseFragment(), MeetingsListMVPView {

    @Inject
    lateinit var presenter: MeetingsListMVPPresenter<MeetingsListMVPView, MeetingsListMVPInteractor>

    private val adapterMeetings = MeetingsListAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meetings_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach(this)
        createMeetingsAdapter()
        initToolbar()
        onClick()
        presenter.getMeetingsList()
    }

    private fun createMeetingsAdapter() {
        rvMeetingsList.apply {
            adapter = adapterMeetings
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun onClick() {
        btnToAddMeetingScreen.setOnClickListener {
            openCreateMeetingFragment()
        }
    }

    private fun initToolbar() {
        requireActivity().tbMeetings.apply {
            if (menu.isNotEmpty()) {
                menu.getItem(0).isVisible = false
            }
            title = getString(R.string.meetings_list)
            navigationIcon = null
            setNavigationOnClickListener(null)
        }
    }

    private fun openCreateMeetingFragment() {
        navFragment(R.id.create_meeting_navigation)
    }

    override fun initMeetingsAdapter(listMeetings: List<MeetingEntity>) {
        adapterMeetings.apply {
            meetingList = listMeetings
            notifyDataSetChanged()
            notifyItemRangeChanged(0, itemCount)
        }
    }

    override fun createNotifications(listMeetings: List<MeetingEntity>) {
        listMeetings.forEachIndexed { index, meeting ->
            meeting.time?.let {
                val alarmManager = requireActivity().getSystemService(ALARM_SERVICE) as AlarmManager
                val hour = 3600000
                val time = SimpleDateFormat(
                    "dd.MM.yyyy HH:mm",
                    Locale.getDefault()
                ).parse("${meeting.date} $it")!!.time
                val deltaTime = time - Date().time
                val broadcastIntent =
                    Intent(activity, NotificationBroadcastReceiver::class.java)
                broadcastIntent.putExtra(
                            NotificationBroadcastReceiver.KEY_USER_NAME,
                            meeting.userName
                        )
                broadcastIntent.putExtra(
                            NotificationBroadcastReceiver.KEY_NOTIFICATION_ID,
                            index
                        )

                val pendingIntent =
                    PendingIntent.getBroadcast(requireActivity(), index, broadcastIntent, 0)
                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    if (deltaTime in 0..hour) {
                        Date().time + 1000
                    } else {
                        time
                    },
                    pendingIntent
                )

            }
        }
    }

    override fun showDBError() {
        Toast.makeText(activity, getString(R.string.error_db), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}