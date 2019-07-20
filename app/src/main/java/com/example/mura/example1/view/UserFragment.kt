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

import com.example.mura.example1.R
import com.example.mura.example1.model.POJO.VKPhoto
import com.example.mura.example1.presenter.UserPresenter
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class UserFragment : Fragment() {

    //сохранение данных в bundle
    companion object{
        fun newInstance(userId:Int): UserFragment {
            val args = Bundle()
            args.putInt("userId",userId)

            val fragment = UserFragment()
            fragment.arguments = args
            return fragment

        }

    }

    private lateinit var mRecyclerView: RecyclerView
    private var mAdapter: UserAdapter? = null
    private var presenter: UserPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user,container,false)
        mRecyclerView = view.findViewById(R.id.userFragment)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        if (presenter == null){
            presenter = UserPresenter(this)
        }
        presenter!!.getPhoto(arguments!!.getInt("userId"))
        return view
    }

    fun updateUi(data: MutableList<VKPhoto>) {
        mAdapter = UserAdapter(data)
        mRecyclerView.adapter = mAdapter
        mAdapter!!.notifyDataSetChanged()
    }

    private inner class UserAdapter(val photoList: MutableList<VKPhoto>): RecyclerView.Adapter<UserAdapter.UserHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {

            val view:View = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item,parent,false)
            return UserHolder(view)
        }

        override fun getItemCount(): Int {
            return photoList.size
        }

        override fun onBindViewHolder(holder: UserHolder, position: Int) {
            if(photoList[ position].url !="") {
                holder.bindDrawable(photoList[position].url)
            }
            holder.mTextView.text = ("Like: " + (photoList[ position]).likes_count.toString())
        }

        private inner class UserHolder(mView:View):RecyclerView.ViewHolder(mView){

            val mPhotoView: ImageView = mView.photo
            val mTextView: TextView = mView.text
            fun bindDrawable(url:String){
                presenter!!.getImage(url,mPhotoView)
            }
        }

    }
}
