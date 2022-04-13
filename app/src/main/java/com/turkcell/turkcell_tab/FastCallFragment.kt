package com.turkcell.turkcell_tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.turkcell.turkcell_tab.model.Contact
import com.turkcell.turkcell_tab.adapter.ContactAdapter
import com.turkcell.turkcell_tab.databinding.FragmentFastCallBinding
import com.turkcell.turkcell_tab.view.ContactView

class FastCallFragment : Fragment(), ContactView {
    lateinit var binding: FragmentFastCallBinding
    var fastCallList = ArrayList<Contact>()

    override fun onResume() {
        refreshViews()
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFastCallBinding.inflate(inflater)

        initializeViews()

        return binding.root
    }

    override fun refreshViews() {
        if(activity is MainActivity){
            fastCallList = (activity as MainActivity).getFastCallList()
            binding.rwcontacts.adapter = ContactAdapter(context,fastCallList, ::itemClick,::itemLongClick)
        }
    }

    override fun itemClick(position : Int) {
        Toast.makeText(context,"Arama Yapılıyor!", Toast.LENGTH_LONG).show()
    }

    override fun itemLongClick(position: Int) { }

    override fun initializeViews(){

        if(activity is MainActivity){
            fastCallList = (activity as MainActivity).getFastCallList()
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.rwcontacts.layoutManager = layoutManager
            binding.rwcontacts.adapter = ContactAdapter(context,fastCallList, ::itemClick,::itemLongClick)
        }
    }

}