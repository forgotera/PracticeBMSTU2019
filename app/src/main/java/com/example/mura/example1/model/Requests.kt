package com.example.mura.example1.model

import android.util.Log
import com.example.mura.example1.presenter.MainPresenter
import com.vk.api.sdk.VK
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url


interface ImageApi{
    @GET
    fun getImage(@Url url: String): Observable<ByteArray>

    companion object Factory {
        fun create(): ImageApi{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl( "https://pp.userapi.com/")
                .build()

            return retrofit.create(ImageApi::class.java)
        }
    }
}


class Requests{
    private val observable = Observable.fromCallable {
        VK.executeSync(VKFriendsRequest())
    }

    //запрос на сервер для получения данных

    fun requestFriend() {
        observable
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(MainPresenter().getObserver())
    }



/*
    fun queueThumbnail(target: T ,url: String){
        Log.i("thread","запущено")
        mRequestMap[target] = url
        mRequestHandler.obtainMessage(MESSAGE_DOWNLOAD,target).sendToTarget()

    }

    override fun onLooperPrepared() {
        mRequestHandler = object : Handler() {
            override fun handleMessage(msg: Message) {
                if (msg.what == MESSAGE_DOWNLOAD) {
                    val target = msg.obj as T
                    Log.i(TAG, "Got a request for URL: " + mRequestMap[target])
                    handleRequest(target)
                }
            }
        }
    }

    private fun handleRequest(target: T){
        try {
             var url: String? = mRequestMap[target]
             var bitmapBytes: Array<Byte> =
        }
    }

     override fun quit(): Boolean{
        mHasQuit = true
        return super.quit()
    }
    */
}
