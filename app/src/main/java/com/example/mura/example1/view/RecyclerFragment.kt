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
import kotlinx.android.synthetic.main.list_item.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class RecyclerFragment : Fragment() {

    companion object{
        const val ARG_VKUser = "VKUser"
        @JvmStatic
        fun newInstance(data:ArrayList<Int>) = RecyclerFragment().apply{
            arguments = Bundle().apply {
                putSerializable(ARG_VKUser,data)
            }

        }
    }

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
        updateUi(arguments?.getSerializable(ARG_VKUser) as ArrayList<Int>)
        return view
    }

    //получаем данные и уставливаем адаптер для recyclerView
    private fun updateUi(data:ArrayList<Int>) {
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

    private inner class TextAdapter(dat: ArrayList<Int>): RecyclerView.Adapter<TextHolder>(){
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
