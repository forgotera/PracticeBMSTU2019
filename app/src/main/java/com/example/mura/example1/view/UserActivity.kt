package com.example.mura.example1.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mura.example1.R

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val id = intent.getIntExtra("userId",0)
        val fm = supportFragmentManager
        val fragment = UserFragment.newInstance(id)
        fm.beginTransaction().add(R.id.userfragmentСontainer,fragment).commit()
        Log.i("userId1", id.toString())
    }
}

