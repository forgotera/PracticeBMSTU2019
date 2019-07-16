package com.example.mura.example1.presenter

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import com.example.mura.example1.model.ImageApi
import com.example.mura.example1.model.Requests
import com.example.mura.example1.model.VKUser
import com.example.mura.example1.view.RecyclerFragmentView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers




class MainPresenter(val view:RecyclerFragmentView) {
    private val friendList: MutableList<VKUser> = arrayListOf()

    private val mRequests = Requests()

    // получаем список друзей
    fun getFriendList(){
        val dataObservable: Observable<List<VKUser>> = mRequests.observable
        dataObservable
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getFriendListObserver())

    }
    private fun getFriendListObserver():Observer<List<VKUser>> {
        return object : Observer<List<VKUser>> {
            override fun onComplete() {
                Log.i("friendlist","${friendList.size}")
                view.updateUi(friendList)
            }

            override fun onSubscribe(d: Disposable) {
                Log.e("onSubscribe:", "$d")

            }

            override fun onNext(s: List<VKUser>) {
                friendList += s
                Log.e("onNext:", "$s")
            }

            override fun onError(e: Throwable) {
                Log.e("onError:", "$e")
            }
        }
    }


}