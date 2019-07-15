package com.example.mura.example1.presenter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.mura.example1.model.ImageApi
import com.example.mura.example1.model.Requests
import com.example.mura.example1.model.VKUser
import com.example.mura.example1.view.MainActivity
import com.example.mura.example1.view.RecyclerFragment
import com.example.mura.example1.view.RecyclerFragmentView
import com.example.mura.example1.view.Welcome
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainPresenter(val view:RecyclerFragmentView) {

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
            }

            override fun onSubscribe(d: Disposable) {
                Log.e("onSubscribe:", "$d")

            }

            override fun onNext(s: List<VKUser>) {
                view.updateUi(s as MutableList<VKUser>)
                Log.e("onNext:", "$s")
            }

            override fun onError(e: Throwable) {
                Log.e("onError:", "$e")
            }
        }
    }

    fun getFriendAvatar(url:String){
        val imageObservable = ImageApi.create()
        imageObservable.getImage(url)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getImageObserver())
    }

    private fun getImageObserver(): Observer<ByteArray> {
        return object : Observer<ByteArray> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
                Log.e("onSubscribe:", "$d")

            }

            override fun onNext(bitmapBytes: ByteArray) {
                 val bitmap = BitmapFactory
                    .decodeByteArray(bitmapBytes, 0, bitmapBytes.size)
                val drawable = BitmapDrawable(bitmap)
                view
                Log.e("onNext:", "$bitmapBytes")
            }

            override fun onError(e: Throwable) {
                Log.e("onError:", "$e")
            }
        }
    }


}