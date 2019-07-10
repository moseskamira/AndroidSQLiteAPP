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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_layout_view.*
import kotlinx.android.synthetic.main.recycler_view.*

class MainActivity : AppCompatActivity() {
    private var playerName: EditText? = null
    private var playerPosition: EditText? = null
    private var saveButton: Button? = null
    private var retrieveButton: Button? = null
    private lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    private val allPlayers:ArrayList<Player> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val toolBar = tool_bar
////        setSupportActionBar(toolBar)
        val fab = fab
        fab.setOnClickListener {
            showDialog()

        }

        recyclerView = recycler_view
        // Set RecyclerView Properties
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()

        myAdapter = MyAdapter(this, allPlayers)

        // Retrieve upon running the App when there is some data in the database
        fetcheAllPlayers()
    }

    private fun showDialog(){
        val dialog = Dialog(this)
//        dialog.requestWindowFeature()
        dialog.setContentView(R.layout.custom_layout_view)
        playerName = findViewById(R.id.p_name)
        playerPosition = findViewById(R.id.p_position)
        saveButton = findViewById(R.id.save_btn)
        retrieveButton = findViewById(R.id.retrieve_btn)
        if(saveButton != null){
            saveButton!!.setOnClickListener {
                savePlayer(playerName!!.text.toString(), playerPosition!!.text.toString())
            }
        }

        if(retrieveButton != null){
            retrieveButton!!.setOnClickListener {
                fetcheAllPlayers()
            }
        }
    }

    fun savePlayer(name: String, position: String){
        val dbAdapter = DBAdapter(this)
        dbAdapter.openDB()
        val result: Long = dbAdapter.inserData(name, position)
        if (result>0) {
            playerName!!.text = p_name.text
            playerPosition!!.text = p_position.text
    }

        dbAdapter.closeDB()

        // Refresh
        fetcheAllPlayers()


    }
    fun fetcheAllPlayers(){
        allPlayers.clear()
        val retDbAdapter: DBAdapter = DBAdapter(this)
        retDbAdapter.openDB()
        val cursorObject: Cursor = retDbAdapter.getAllPlayers()
        while (cursorObject.moveToNext()){
            val playerId: Int = cursorObject.getInt(0)
            val playerName: String = cursorObject.getString(1)
            val playerPosition: String = cursorObject.getString(2)
            val player = Player(playerId, playerName, playerPosition, R.id.player_image)
            allPlayers.add(player)

        }
        if (allPlayers.size > 0) {
            recyclerView.adapter
        }
        retDbAdapter.closeDB()
    }
}
