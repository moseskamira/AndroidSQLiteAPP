package com.example.myapplication

import android.app.Dialog
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.adapter.MyAdapter
import com.example.myapplication.model.Player
import com.example.myapplication.sql.DBAdapter
import com.example.myapplication.sql.DBHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycler_view.*

class MainActivity : AppCompatActivity() {
    private var playerName: EditText? = null
    private var playerPosition: EditText? = null
    private var saveButton: Button? = null
    private var retrieveButton: Button? = null
    private lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    private val allPlayers:ArrayList<Player> = ArrayList()
    lateinit var myDatabase: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myDatabase = DBHelper(this)
        val fab = fab
        fab.setOnClickListener {
            showDialog()

        }

        recyclerView = recycler_view
        // Set RecyclerView Properties
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()

        myAdapter = MyAdapter(this, allPlayers)
        recyclerView.adapter = myAdapter
        recyclerView.setHasFixedSize(true)

        // Retrieve upon running the App when there is some data in the database
        fetchAllPlayers()
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_layout_view)
        playerName = dialog.findViewById(R.id.p_name)
        playerPosition = dialog.findViewById(R.id.p_position)
        saveButton = dialog.findViewById(R.id.save_btn)
        retrieveButton = dialog.findViewById(R.id.retrieve_btn)
        savePlayer()

        if(retrieveButton != null){
            retrieveButton!!.setOnClickListener {
                fetchAllPlayers()
            }
        }
        dialog.show()
    }

    private fun savePlayer() {
        val dbAdapter = DBAdapter(this)
        dbAdapter.openDB()
        if(saveButton != null){
            saveButton!!.setOnClickListener {
                val isInserted: Boolean = dbAdapter.insertData(playerName!!.text.toString(), playerPosition!!.text.toString())
                if(isInserted) {
                    Toast.makeText(this, "Player Save", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "Unable To Save", Toast.LENGTH_SHORT).show()
                }
                fetchAllPlayers()
            }
        }

        // Refresh
        fetchAllPlayers()
    }

    private fun fetchAllPlayers() {
//        allPlayers.clear()
        val retDbAdapter = DBAdapter(this)
        retDbAdapter.openDB()
        val cursorObject: Cursor = retDbAdapter.getAllPlayers()
        while (cursorObject.moveToNext()){
            val playerId: Int = cursorObject.getInt(0)
            val playerName: String = cursorObject.getString(1)
            val playerPosition: String = cursorObject.getString(2)
            val player = Player(playerId, playerName, playerPosition, R.id.player_image)
            allPlayers.add(player)

        }
        retDbAdapter.closeDB()
        myAdapter.setItems(allPlayers)
    }
}
