package com.example.mura.example1.view


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mura.example1.R
import com.example.mura.example1.model.*
import com.example.mura.example1.presenter.MainPresenter
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.list_item.view.*
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.io.IOException
import java.lang.Thread.sleep
import java.net.SocketException


/**
 * A simple [Fragment] subclass.
 *
 */
class RecyclerFragment : Fragment() {

    private lateinit var mRecyclerView : RecyclerView
    private var mAdapter: FriendsListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance

    }

    override fun onResume() {
        super.onResume()
        val mMainPresenter = MainPresenter().getClass()
        mMainPresenter.getFriendList()
    }

    fun getClass(): RecyclerFragment {
        Log.i("classRep1",this@RecyclerFragment.toString())
        return this@RecyclerFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recycler, container, false)
        Log.i("recyclerView", view.toString())
        mRecyclerView = view.findViewById(R.id.recyclerview)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        return view
    }

    //получаем данные и уставливаем адаптер для recyclerView
    fun updateUi(data: MutableList<VKUser>) {
        mAdapter = FriendsListAdapter(data)
        mRecyclerView.adapter = mAdapter //Fixme 1 mRecyclerView = null, не выполняется onCreateView
    }

    private inner class FriendsListHolder( mView: View) : RecyclerView.ViewHolder(mView),View.OnClickListener{
        //click
        init {
            mView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            Toast.makeText(activity,"work",Toast.LENGTH_LONG).show()
        }

        //связываиние textView и передоваемой информации
        val mTextView: TextView = mView.text
        val mPhotoView: ImageView = mView.photo
    }

    private inner class FriendsListAdapter(data: MutableList<VKUser>): RecyclerView.Adapter<FriendsListHolder>(){
        //данные
        private val friendList = data

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FriendsListHolder {
            val view: View = LayoutInflater.from(p0.context).inflate(R.layout.list_item,p0,false)
            return FriendsListHolder(view)
        }

        //кол-во элементов
        override fun getItemCount(): Int {
            return  friendList.size
        }

        //Вывод данных на экран
        override fun onBindViewHolder(p0: FriendsListHolder, p1: Int) {
            p0.mTextView.text = (friendList[p1]).firstName
            p0.mPhotoView.setImageDrawable(resources.getDrawable(R.drawable.baseline_photo_camera_black_18dp))
            Log.d("photo",friendList[p1].photo)
            //downloadImage(friendList[p1].photo)

        }



    }

    //все что ниже должно быть в model
/*
    fun dowloadImage(url:String){
        //загрузка байтов по url
       // val bitmapBytes = getUrlBytes
        val bitmap: Bitmap = BitmapFactory.decodeByteArray(bitmapBytes,0,bitmapBytes.size)

    }
    */

   private fun downloadImage(url:String){
       /*
       RxJavaPlugins.setErrorHandler { e ->
           if (e is UndeliverableException) {
               // Merely log undeliverable exceptions
               Log.e("error",e.message)
           } else {
               // Forward all others to current thread's uncaught exception handler
               Thread.currentThread().also { thread ->
                   thread.uncaughtExceptionHandler.uncaughtException(thread, e)
               }
           }
       }
       */
        //ретрофит позволяет динамически переопределить url поэтому передаем его в интерфейс
        val imageService = ImageApi.create()
       //fixme
        imageService.getImage(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    value -> handleResult(value)
            },
                {
                        error -> error.printStackTrace()}
            )

   }

    fun handleResult(value:ByteArray){
        Log.i("qwer",value.toString())
    }

/*
    private fun handleResult(): Observer<ByteArray> {
        return object : Observer<ByteArray> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNext(t: ByteArray) {
                Log.d("onnext",t.toString())
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }


        }

    }
    */

}
