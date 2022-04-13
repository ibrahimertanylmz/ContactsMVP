package com.turkcell.turkcell_tab.presenter

import com.turkcell.turkcell_tab.model.Contact

interface MainPresenter {
    fun addContacts(): ArrayList<Contact>
}