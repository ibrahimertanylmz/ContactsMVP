package com.turkcell.turkcell_tab.presenter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.turkcell.turkcell_tab.MainActivity
import com.turkcell.turkcell_tab.model.Contact

interface ContactsPresenter {
    fun showAlert(position: Int, blacklist: ArrayList<Contact>, context: Context, activity: MainActivity, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?)
}