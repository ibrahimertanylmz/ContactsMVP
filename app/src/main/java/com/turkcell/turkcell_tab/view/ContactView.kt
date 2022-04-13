package com.turkcell.turkcell_tab.view

interface ContactView {
    fun initializeViews()
    fun itemClick(position: Int)
    fun itemLongClick(position: Int)
    fun refreshViews()
}