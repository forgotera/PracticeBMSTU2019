package com.example.mura.example1.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.mura.example1.R
import com.example.mura.example1.model.VKFriendsRequest
import com.example.mura.example1.model.VKUser
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import kotlinx.android.synthetic.main.list_item.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class RecyclerFragment : Fragment() {


    //fixme нужно передать сюда список VKUser, но bundle принимает только примитивы
    //fixme передавать два отдельных массива для фото и имени?
    /*companion object{
        const val ARG_VKUser = "VKUser"
        @JvmStatic
        fun newInstance(data:List<mad>) = RecyclerFragment().apply{
            val mValue: List<mad> = data
            arguments = Bundle().apply {
                putParcelable(ARG_VKUser, data)
            }

        }
    }*/

    private lateinit var mRecyclerView : RecyclerView
    private var mAdapter: TextAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recycler, container, false)
        mRecyclerView = view.findViewById(R.id.recyclerview)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        //получаем данные
        request()
        return view
    }

    //wtf вообще как сделать так чтобы запрос шел с model
    //wtf данные с result не выходят за пределы object
    private fun request(){
        VK.execute(VKFriendsRequest(), object : VKApiCallback<List<VKUser>> {
            override fun success(result: List<VKUser>) {
                updateUi(result as MutableList<VKUser>)
            }

            override fun fail(error: VKApiExecutionException) {
            }
        })

    }

    //получаем данные и уставливаем адаптер для recyclerView
    private fun updateUi(data: MutableList<VKUser>) {
        mAdapter = TextAdapter(data)
        mRecyclerView.adapter = mAdapter
    }

    private inner class TextHolder( mView: View) : RecyclerView.ViewHolder(mView),View.OnClickListener{
        //click
        init {
            mView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            Toast.makeText(activity,"work",Toast.LENGTH_LONG).show()
        }

        //связываиние textView и передоваемого текста
        val mTextView: TextView = mView.text
    }

    private inner class TextAdapter(dat: MutableList<VKUser>): RecyclerView.Adapter<TextHolder>(){
        //данные
        private val dateMad = dat

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TextHolder {
            val view: View = LayoutInflater.from(p0.context).inflate(R.layout.list_item,p0,false)
            return TextHolder(view)
        }

        //кол-во элементов
        override fun getItemCount(): Int {
            return  dateMad.size
        }

        //Вывод данных на экран
        override fun onBindViewHolder(p0: TextHolder, p1: Int) {
            p0.mTextView.text = dateMad[p1].toString()
        }

    }



}
