package com.example.mura.example1.model


class mad(private val text:String) {

    fun createDate():ArrayList<Int>{
       val array = ArrayList<Int>(100)
        for (i in 0..100){
            array.add(i)
        }
        return array
    }
}