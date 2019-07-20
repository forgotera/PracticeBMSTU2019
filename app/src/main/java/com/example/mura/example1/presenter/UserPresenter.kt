package com.example.mura.example1.presenter

import android.util.Log
import android.widget.ImageView
import com.example.mura.example1.model.Requests
import com.example.mura.example1.model.POJO.VKPhoto
import com.example.mura.example1.view.UserFragment
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserPresenter(val view:UserFragment) {
    private val mRequests = Requests()
    private val photoList:MutableList<VKPhoto> = arrayListOf()

    fun getPhoto(userId:Int){
        val dataObservable: Observable<List<VKPhoto>> = mRequests.photoRequest(userId)
        dataObservable
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getPhotoObserver())

    }

    private fun getPhotoObserver(): Observer<List<VKPhoto>> {
        return object : Observer<List<VKPhoto>> {
            override fun onComplete() {
                view.updateUi(photoList)
                Log.i("photoList", photoList.toString())

            }

            override fun onSubscribe(d: Disposable) {
                Log.e("onSubscribeUser:", "$d")

            }

            override fun onNext(s: List<VKPhoto>) {
                photoList += s
                Log.e("onNextUser:", "$s")
            }

            override fun onError(e: Throwable) {
                Log.e("onError:", "$e")
            }
        }
    }

    fun getImage(url:String,mPhotoView: ImageView){
        Picasso.get()
            .load(url)
            .placeholder(com.example.mura.example1.R.drawable.baseline_photo_camera_black_18dp)
            .resize(510,714)
            .into(mPhotoView)
    }


}