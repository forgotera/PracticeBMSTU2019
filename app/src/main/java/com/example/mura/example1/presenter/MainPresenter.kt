package com.example.mura.example1.presenter

import com.example.mura.example1.R
import com.example.mura.example1.view.RecyclerFragment
import com.example.mura.example1.model.mad

class MainPresenter {
    private lateinit var  mRecyclerFragment: RecyclerFragment
    private lateinit var mVKuser: mad

    public fun getData():ArrayList<Int>{
        val textData = mad("murad")
        val data = textData.createDate()
        return  data
    }
}