package com.example.mura.example1.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.mura.example1.model.*
import com.example.mura.example1.presenter.MainPresenter
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
        val view = inflater.inflate(com.example.mura.example1.R.layout.fragment_recycler, container, false)
        mRecyclerView = view.findViewById(com.example.mura.example1.R.id.recyclerview)
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



    interface OnFriendClickListener{
        fun onFriendClick(vkUser: VKUser)
    }

    private inner class FriendsListAdapter(val friendList: MutableList<VKUser>): RecyclerView.Adapter<FriendsListAdapter.FriendsListHolder>(){

        private val mOnFriendClickListener: OnFriendClickListener? = object : OnFriendClickListener{
            override fun onFriendClick(vkUser: VKUser) {
                presenter?.onFriendClick(context,vkUser)

            }
        }


        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FriendsListHolder {
            val view: View = LayoutInflater.from(p0.context).inflate(com.example.mura.example1.R.layout.list_item,p0,false)
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


        private inner class FriendsListHolder( mView: View) : RecyclerView.ViewHolder(mView),View.OnClickListener{
            //click
            init {
                mView.setOnClickListener(this)
            }

            override fun onClick(p0: View?) {
                val vkUser = friendList[layoutPosition]
                mOnFriendClickListener?.onFriendClick(vkUser)
            }

            //связываиние textView и передоваемой информации
            val mTextView: TextView = mView.text
            val mPhotoView: ImageView = mView.photo
            fun bindDrawable(url:String){
                //src пустое
                presenter!!.getImage(url,mPhotoView)
            }
        }
    }
}
