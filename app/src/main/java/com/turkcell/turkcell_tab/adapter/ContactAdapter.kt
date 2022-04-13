package com.turkcell.turkcell_tab.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.turkcell.turkcell_tab.model.Contact
import com.turkcell.turkcell_tab.R

class ContactAdapter(var context: Context?, var liste: ArrayList<Contact>, var itemClick : (position:Int)->Unit, var itemLongClick : (position:Int)->Unit) : RecyclerView.Adapter<ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.contact_item,parent,false)
        return ContactViewHolder(v,itemClick,itemLongClick)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindData(liste.get(position))
    }

    override fun getItemCount(): Int {
        return liste.size
    }
}