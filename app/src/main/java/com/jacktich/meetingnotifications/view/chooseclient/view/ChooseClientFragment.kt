package com.jacktich.meetingnotifications.view.chooseclient.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jacktich.meetingnotifications.R
import com.jacktich.meetingnotifications.data.api.responses.DataUser
import com.jacktich.meetingnotifications.utils.navFragment
import com.jacktich.meetingnotifications.view.base.view.BaseFragment
import com.jacktich.meetingnotifications.view.chooseclient.adapters.RandomUserAdapter
import com.jacktich.meetingnotifications.view.chooseclient.interactor.ChooseClientMVPInteractor
import com.jacktich.meetingnotifications.view.chooseclient.models.UserModel
import com.jacktich.meetingnotifications.view.chooseclient.presenter.ChooseClientMVPPresenter
import kotlinx.android.synthetic.main.activity_meetings.*
import kotlinx.android.synthetic.main.fragment_choose_client.*
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

class ChooseClientFragment: BaseFragment(), ChooseClientMVPView {

    companion object {
        const val KEY_USER_BUNDLE = "keyUserBundle"
    }

    @Inject
    lateinit var presenter: ChooseClientMVPPresenter<ChooseClientMVPView, ChooseClientMVPInteractor>

    private val adapterUsers = RandomUserAdapter(listOf(), object: RandomUserAdapter.OnRandomUserClick{
        override fun onClick(user: DataUser) {
            addUserInBundle(user)
            navFragment(R.id.create_meeting_navigation, arguments)
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_client, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onAttach(this)
        initToolbar()
        createRvRandomUser()
        presenter.getRandomUserList()
    }



    override fun initRandomUserAdapter(usersList: List<DataUser>) {
        adapterUsers.apply {
            this.usersList = usersList
            notifyDataSetChanged()
            notifyItemRangeChanged(0, itemCount)
        }
    }

    private fun addUserInBundle(user: DataUser){
        val userModel = UserModel(
            img = user.picture.medium,
            name = "${user.name.title} ${user.name.first} ${user.name.last}",
            email = user.email
        )
        if (arguments == null){
            val bundle = Bundle()
            bundle.putParcelable(KEY_USER_BUNDLE, userModel)
            arguments = bundle
        }else{
            requireArguments().putParcelable(KEY_USER_BUNDLE, userModel)
        }
    }

    private fun clearUserAdapter(){
        adapterUsers.apply {
            usersList = listOf()
            notifyDataSetChanged()
        }
    }

    private fun initToolbar(){
        requireActivity().tbMeetings.apply {
            if(menu.isNotEmpty()){
                menu.getItem(0).isVisible = true
            }
            setOnMenuItemClickListener {
                if(it.itemId == R.id.itemRefreshMenuChooseUser){
                    clearUserAdapter()
                    presenter.getRandomUserList()
                    return@setOnMenuItemClickListener true
                }
                return@setOnMenuItemClickListener false
            }
            title = context.getString(R.string.choose_client)
            navigationIcon = ContextCompat.getDrawable(requireActivity(), R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                navFragment(R.id.create_meeting_navigation, arguments)
            }
        }
    }

    private fun createRvRandomUser(){
        rvRandomUser.apply {
            adapter = adapterUsers
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    override fun showServerMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showNoConnectionError() {
        Toast.makeText(activity, getString(R.string.error_no_connection), Toast.LENGTH_SHORT).show()
    }

    override fun showErrorCode(code: Int) {
        when(code){
            HttpsURLConnection.HTTP_UNAVAILABLE -> showServerMessage(getString(R.string.error_server_503))
            else -> showServerMessage(getString(R.string.error_server))
        }
    }

    override fun showError() {
        Toast.makeText(activity, context?.getString(R.string.error_unknown), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}