package com.turkcell.turkcell_tab.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.turkcell.turkcell_tab.model.Contact
import com.turkcell.turkcell_tab.R

class ContactViewHolder(itemView: View, var itemClick : (position:Int)->Unit,var itemLongClick : (position:Int)->Unit) : RecyclerView.ViewHolder (itemView) {

    var tvName : TextView
    var tvSurname : TextView
    var tvPhone : TextView
    var imageStar : ImageView

    init {
        tvName = itemView.findViewById(R.id.tvname)
        tvSurname = itemView.findViewById(R.id.tvsurname)
        tvPhone = itemView.findViewById(R.id.tvphone)
        imageStar = itemView.findViewById(R.id.imagestar)

        itemView.setOnClickListener {
            itemClick(adapterPosition)
        }

        itemView.setOnLongClickListener {
            itemLongClick(adapterPosition)
            true
        }

    }

    fun bindData(contact: Contact){
        tvName.text = contact.name
        tvSurname.text = contact.surname
        tvPhone.text = contact.phone
        if (contact.isFastCall){
            imageStar.visibility = View.VISIBLE
        }else{
            imageStar.visibility = View.INVISIBLE
        }
    }
}