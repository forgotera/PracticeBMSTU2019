package com.example.mura.example1.view


import android.graphics.drawable.Drawable
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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
interface RecyclerFragmentView{
    fun updateUi(data: MutableList<VKUser>)
}

class RecyclerFragment : Fragment(), RecyclerFragmentView {

    private lateinit var mRecyclerView : RecyclerView
    private var mAdapter: FriendsListAdapter? = null
    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance

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
        if (presenter == null){
            presenter = MainPresenter(this)
        }
        presenter!!.getFriendList()
        return view
    }

    //получаем данные и уставливаем адаптер для recyclerView
    override fun updateUi(data: MutableList<VKUser>) {
        mAdapter = FriendsListAdapter(data)
        mRecyclerView.adapter = mAdapter
        mAdapter!!.notifyDataSetChanged()
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
        fun bindDrawable(url:String){

            Picasso.get()
                .load(url)
                .placeholder(R.drawable.baseline_photo_camera_black_18dp)
                .into(mPhotoView)

        }
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
            if(friendList[p1].photo != "") {
                p0.bindDrawable(friendList[p1].photo)
            }
            p0.mTextView.text = (friendList[p1]).firstName
        }
    }
}
