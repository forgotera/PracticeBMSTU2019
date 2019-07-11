package com.example.mura.example1.presenter

import android.util.Log
import com.example.mura.example1.model.Requests
import com.example.mura.example1.model.VKUser
import com.example.mura.example1.view.MainActivity
import com.example.mura.example1.view.RecyclerFragment
import com.example.mura.example1.view.Welcome
import io.reactivex.disposables.Disposable
import io.reactivex.Observer


class MainPresenter {
    var friendsList: MutableList<VKUser> = arrayListOf()

    fun getClass(): MainPresenter {
        return this@MainPresenter
    }

    fun startMainActivity(welcome: Welcome) {
        MainActivity.startFrom(welcome) // создание активити и фрагемента RecyclerView в нем
       // getFriendList() // создание класса request и запрос на сервер с rxJava
    }

    fun getFriendList(){
        val requests = Requests()
        requests.requestFriend()
    }

    //подписываемся на данные их Requests
    fun getObserver():Observer<List<VKUser>> {
        return object : Observer<List<VKUser>> {
            override fun onComplete() {
                val mRecyclerFragment = RecyclerFragment().getClass() // получаю объект
                mRecyclerFragment.updateUi(friendsList)  //Fixme 1 в методе upadteUi() mRecyclerView = null
            }

            override fun onSubscribe(d: Disposable) {
                Log.e("onSubscribe:", "$d")

            }

            override fun onNext(s: List<VKUser>) {
                friendsList = s.toMutableList()
                Log.e("onNext:", "$s")
                Log.e("sizeOnNext:","${s.size}")
            }

            override fun onError(e: Throwable) {
                Log.e("onError:", "$e")
            }
        }
    }

}