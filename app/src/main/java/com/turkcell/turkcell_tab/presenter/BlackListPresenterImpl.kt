package com.turkcell.turkcell_tab.presenter

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.turkcell.turkcell_tab.MainActivity
import com.turkcell.turkcell_tab.model.Contact

class BlackListPresenterImpl : BlackListPresenter {
    override fun showAlert(position: Int, blacklist: ArrayList<Contact>, context: Context, activity: MainActivity, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?) {
            val adb : AlertDialog.Builder = context.let { AlertDialog.Builder(it) }
            adb!!.setTitle("Çıkar")
            adb.setMessage("Kara Listeden Çıkarmak İstediğinize Emin Misiniz?")
            adb.setPositiveButton("Evet", DialogInterface.OnClickListener { dialog, which ->
                (activity).removeFromBlackList(blacklist.get(position))
                adapter!!.notifyDataSetChanged()
            })
            adb.setNegativeButton("Hayır", DialogInterface.OnClickListener { dialog, which -> })
            val uyari : AlertDialog = adb.create()
            uyari.show()

    }
}