package com.example.mura.example1.presenter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.widget.ImageView
import com.example.mura.example1.model.Requests
import com.example.mura.example1.model.VKUser
import com.example.mura.example1.view.RecyclerFragmentView
import com.example.mura.example1.view.UserActivity
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers




class MainPresenter(val view:RecyclerFragmentView) {
    private val friendList: MutableList<VKUser> = arrayListOf()
    val USER_ID = "userId"

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
                view.updateUi(friendList)
                /*
                val imageObservable = ImageApi.create()
                for(photo in friendList) {
                    imageObservable.getImage(photo.photo)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(getImageObserver())
                }
                */

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

    //загружает ихображения в RecyclerFragment
    fun getImage(url:String,mPhotoView:ImageView){
        Picasso.get()
            .load(url)
            .placeholder(com.example.mura.example1.R.drawable.baseline_photo_camera_black_18dp)
            .into(mPhotoView)
    }

    fun onFriendClick(fragment: Context?, vkUser: VKUser){
        val intent = Intent(fragment,UserActivity::class.java).apply {
            putExtra(USER_ID, vkUser.id)
        }
        startActivity(fragment!!,intent,null)
    }




    /*
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

                Log.e("onNext:", "$bitmapBytes")
            }

            override fun onError(e: Throwable) {
                Log.e("onError:", "$e")
            }
        }
    }
    */


}