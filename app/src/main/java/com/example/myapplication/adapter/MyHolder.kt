package com.example.myapplication.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_list_view.view.*

class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val playerImage: ImageView? = itemView.player_image
    val playerName: TextView? = itemView.player_name
    val playerPosition: TextView? = itemView.player_position
}