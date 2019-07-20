package com.example.mura.example1.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.mura.example1.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fm = supportFragmentManager
        val fragment = RecyclerFragment()
        fm.beginTransaction().add(R.id.fragmentСontainer,fragment).commit()
    }

    companion object {
        fun startFrom(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}
