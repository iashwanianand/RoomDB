package com.example.roomdb

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.roomdb.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataBase: ContactDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataBase = ContactDataBase.getDatabase(this)
//        val dataBase2 = ContactDataBase.getDatabase(this)

        GlobalScope.launch {
            dataBase.contactDao()
                .insertContact(Contact(0, "Ashwani", "ashwania@chetu.com", "", Date()))
        }
    }

    fun getData(view: View) {
        dataBase.contactDao().getContact().observe(this, Observer {
            Log.d("DatabaseData", it.toString())
        })
    }
}