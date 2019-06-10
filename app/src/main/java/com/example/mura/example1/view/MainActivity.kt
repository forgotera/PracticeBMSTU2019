package com.example.mura.example1.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.mura.example1.R
import com.example.mura.example1.presenter.MainPresenter

class MainActivity : AppCompatActivity() {
    private val mMainPresenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFragment()

    }

    private fun createFragment(){
        val fm = supportFragmentManager
        val fragment = RecyclerFragment.newInstance(mMainPresenter.getData())
        fm.beginTransaction().replace(R.id.fragmentСontainer,fragment).commit()
    }

    companion object {
        private const val TAG = "MainActivity"

        private const val IMAGE_REQ_CODE = 101

        fun startFrom(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}
