package com.turkcell.turkcell_tab.presenter

import com.turkcell.turkcell_tab.model.Contact

class MainPresenterImpl : MainPresenter{

    override fun addContacts() : ArrayList<Contact>{
        val contactList = ArrayList<Contact>()
        for (i in 0..30){
            val contact = Contact()
            contact.id = i
            contact.name = "Ad" + i
            contact.surname = "Soyad" + i
            contact.phone = "0555 231 " + i
            contactList.add(contact)
        }
        return contactList
    }

}