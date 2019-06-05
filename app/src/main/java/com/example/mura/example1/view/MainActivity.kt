package com.example.mura.example1.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.mura.example1.R
import com.example.mura.example1.presenter.RecyclerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm = supportFragmentManager;
        val fragment = RecyclerFragment()
        fm.beginTransaction().add(R.id.fragmentСontainer,fragment).commit()
    }
}
