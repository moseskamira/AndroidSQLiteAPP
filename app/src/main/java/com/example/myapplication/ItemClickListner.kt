package com.example.myapplication

import android.view.View

interface ItemClickListner {
    fun onItemClick(view: View, position: Int)
}