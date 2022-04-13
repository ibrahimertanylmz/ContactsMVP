package com.turkcell.turkcell_tab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.turkcell.turkcell_tab.databinding.ActivityMainBinding
import com.turkcell.turkcell_tab.databinding.TabLayoutBinding
import com.turkcell.turkcell_tab.model.Contact
import com.turkcell.turkcell_tab.presenter.MainPresenterImpl
import com.turkcell.turkcell_tab.view.MainView

class MainActivity : AppCompatActivity(), MainView  {
    lateinit var binding : ActivityMainBinding
    private var contactList = ArrayList<Contact>()
    private var blackList = ArrayList<Contact>()
    var presenter = MainPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
    }

    private fun initializeViews() {
        contactList = presenter.addContacts()
        viewPagerOlustur()
        tabOlustur()
    }

    fun getAllContacts(): ArrayList<Contact>{
        return contactList
    }

    fun getBlackList(): ArrayList<Contact> {
        return blackList
    }

    fun getContact(position: Int): Contact {
        return contactList.get(position)
    }

    fun addToBlackList(position: Int){
        val contact : Contact = contactList.get(position)
        contact.isFastCall = false
        contact.isBlackList = true
        blackList.add(contact)
        contactList.remove(contact)
    }

    fun removeFromBlackList(contact: Contact){
        contact.isFastCall = false
        contact.isBlackList = false
        blackList.remove(contact)
        contactList.add(contact)
    }

    fun getFastCallList(): ArrayList<Contact>{
        val fastCallList = ArrayList<Contact>()
        contactList.forEach {
            if(it.isFastCall){
                fastCallList.add(it)
            }
        }
        return fastCallList
    }

    override fun tabOlustur() {

        TabLayoutMediator(binding.tabLayout, binding.viewpager){
            tab,position->
        }.attach()

        val tab = TabLayoutBinding.inflate(layoutInflater)
        tab.imageView.setImageResource(R.drawable.fastcall)

        val tab2 = TabLayoutBinding.inflate(layoutInflater)
        tab2.imageView.setImageResource(R.drawable.contacts)

        val tab3 = TabLayoutBinding.inflate(layoutInflater)
        tab3.imageView.setImageResource(R.drawable.blacklist)


        tab.tvAciklama.visibility = View.VISIBLE
        tab2.tvAciklama.visibility = View.VISIBLE
        tab.tvAciklama.text = "Hızlı aramalar"
        tab2.tvAciklama.text = "Tüm Kişiler"

        tab3.tvAciklama.visibility = View.VISIBLE
        tab3.tvAciklama.text = "Kara Liste"


        binding.tabLayout.getTabAt(0)!!.setCustomView(tab.root)
        binding.tabLayout.getTabAt(1)!!.setCustomView(tab2.root)
        binding.tabLayout.getTabAt(2)!!.setCustomView(tab3.root)

    }

    override fun viewPagerOlustur() {
        val adapter = ViewPagerAdapter(this)
        adapter.fragmentEkle(FastCallFragment())
        adapter.fragmentEkle(ContactsFragment())
        adapter.fragmentEkle(BlackListFragment())
        binding.viewpager.adapter = adapter
    }
    
    internal class ViewPagerAdapter(activity: FragmentActivity):FragmentStateAdapter(activity)
    {
        private val fragmentList = ArrayList<Fragment>()

        override fun getItemCount(): Int {
            return fragmentList.size
        }
        override fun createFragment(position: Int): Fragment {
            return fragmentList.get(position)
        }
        fun fragmentEkle(fragment: Fragment){
            fragmentList.add(fragment)
        }
    }
}