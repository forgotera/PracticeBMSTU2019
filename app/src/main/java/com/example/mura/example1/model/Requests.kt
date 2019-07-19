package com.example.mura.example1.model

import android.util.Log
import com.example.mura.example1.view.UserActivity
import com.vk.api.sdk.VK
import io.reactivex.Observable


/*
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
*/


class Requests{

    val observable = Observable.fromCallable {
        VK.executeSync(VKFriendsRequest())
    }

    fun photoRequest(userId:Int): Observable<List<VKPhoto>> {
        return Observable.fromCallable {
            VK.executeSync(VKPhotosRequest(userId))
        }
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
