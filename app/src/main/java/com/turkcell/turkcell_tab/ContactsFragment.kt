package com.turkcell.turkcell_tab

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.turkcell.turkcell_tab.model.Contact
import com.turkcell.turkcell_tab.adapter.ContactAdapter
import com.turkcell.turkcell_tab.databinding.FragmentContactsBinding
import com.turkcell.turkcell_tab.presenter.ContactsPresenterImpl
import com.turkcell.turkcell_tab.view.ContactView

class ContactsFragment : Fragment(), ContactView {
    lateinit var binding: FragmentContactsBinding
    var contacts = ArrayList<Contact>()
    private val contactsPresenter = ContactsPresenterImpl()

    override fun onResume() {
        refreshViews()
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactsBinding.inflate(inflater)

        initializeViews()

        return binding.root
    }

    override fun itemClick(position: Int) {
        Toast.makeText(context,"Arama Yapılıyor!", Toast.LENGTH_LONG).show()
    }

    override fun itemLongClick(position: Int) {
        context?.let { contactsPresenter.showAlert(position,contacts, it,activity as MainActivity, binding.rwcontacts.adapter) }
    }

    override fun refreshViews() {
        if(activity is MainActivity){
            contacts = (activity as MainActivity).getAllContacts()
            binding.rwcontacts.adapter!!.notifyDataSetChanged()
        }
    }

    override fun initializeViews(){
        if(activity is MainActivity){
            contacts = (activity as MainActivity).getAllContacts()
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.rwcontacts.layoutManager = layoutManager
            binding.rwcontacts.adapter = ContactAdapter(context,contacts, ::itemClick,::itemLongClick)
        }
    }

}