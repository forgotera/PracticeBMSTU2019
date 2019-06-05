package com.example.mura.example1.presenter


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mura.example1.R
import com.example.mura.example1.model.mad
import kotlinx.android.synthetic.main.list_item.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class RecyclerFragment : Fragment() {

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
        updateUi()
        return view
    }

    private fun updateUi() {
        val textData = mad("murad")
        val date = textData.createDate()
        mAdapter = TextAdapter(date)
        mRecyclerView.adapter = mAdapter
    }

    private inner class TextHolder(val mView: View) : RecyclerView.ViewHolder(mView){
        val mTextView: TextView = mView.text
    }

    private inner class TextAdapter(dat: ArrayList<Int>): RecyclerView.Adapter<TextHolder>(){
        private val dateMad = dat

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TextHolder {
            val view: View = LayoutInflater.from(p0.context).inflate(R.layout.list_item,p0,false)
            return TextHolder(view)
        }

        override fun getItemCount(): Int {
            return  dateMad.size
        }

        override fun onBindViewHolder(p0: TextHolder, p1: Int) {
            p0.mTextView.text = dateMad[p1].toString()
        }

    }


}
