package com.turkcell.turkcell_tab

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.turkcell.turkcell_tab.model.Contact
import com.turkcell.turkcell_tab.adapter.ContactAdapter
import com.turkcell.turkcell_tab.databinding.FragmentBlacklistBinding
import com.turkcell.turkcell_tab.presenter.BlackListPresenterImpl
import com.turkcell.turkcell_tab.view.ContactView

class BlackListFragment : Fragment(), ContactView {

    lateinit var binding: FragmentBlacklistBinding
    var blackList = ArrayList<Contact>()
    private var blPresenter = BlackListPresenterImpl()

    override fun onResume() {
        refreshViews()
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlacklistBinding.inflate(inflater)

        initializeViews()

        return binding.root
    }

    override fun itemLongClick(position: Int) {
        context?.let { blPresenter.showAlert(position,blackList, it, activity as MainActivity,binding.rwcontacts.adapter) }
    }

    override fun itemClick(position : Int) { }

    override fun refreshViews(){
        if(activity is MainActivity){
            blackList = (activity as MainActivity).getBlackList()
            binding.rwcontacts.adapter!!.notifyDataSetChanged()
        }
    }

    override fun initializeViews(){
        if(activity is MainActivity){
            blackList = (activity as MainActivity).getBlackList()
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.rwcontacts.layoutManager = layoutManager
            binding.rwcontacts.adapter = ContactAdapter(context,blackList, ::itemClick,::itemLongClick)
        }
    }
}