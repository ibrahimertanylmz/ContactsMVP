package com.turkcell.turkcell_tab.presenter

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.turkcell.turkcell_tab.MainActivity
import com.turkcell.turkcell_tab.model.Contact

class ContactsPresenterImpl : ContactsPresenter {
    override fun showAlert(position: Int, blacklist: ArrayList<Contact>, context: Context, activity: MainActivity, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?) {
        val adb : AlertDialog.Builder? = context?.let { AlertDialog.Builder(it) }
        adb!!.setTitle("Ekle")
        adb.setMessage("Hızlı Aramaya mı Yoksa Kara Listeye mi Eklemek İstersiniz?")
        adb.setPositiveButton("Hızlı Arama", DialogInterface.OnClickListener { dialog, which ->
            (activity).getContact(position).isFastCall = true
            adapter!!.notifyDataSetChanged()
        })
        adb.setNegativeButton("Kara Liste", DialogInterface.OnClickListener { dialog, which ->
            (activity).getContact(position).isBlackList = true
            (activity).addToBlackList(position)
            adapter!!.notifyDataSetChanged()
        })
        val uyari : AlertDialog = adb.create()
        uyari.show()
    }
}