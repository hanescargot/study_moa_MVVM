package com.pyrion.studymoa.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pyrion.studymoa.databinding.ItemStudyBinding
import com.pyrion.studymoa.utils.StudyDTO


class BottomSheetRecyclerViewAdapter(
    val context: Context,
    private var items: LiveData<ArrayList<StudyDTO>>
    ) : RecyclerView.Adapter<BottomSheetRecyclerViewAdapter.RecyclerViewHoler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHoler {
        Log.i("!!", "ddcc")
        val binding = ItemStudyBinding.inflate(LayoutInflater.from(context), parent, false)
        return RecyclerViewHoler(binding, context)
    }

    override fun onBindViewHolder(holder: RecyclerViewHoler, position: Int) {
        items.value?.get(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        Log.i("!!", items?.value?.size!!.toString())
        return items?.value?.size!!
    }

    inner class RecyclerViewHoler (private val binding: ItemStudyBinding, val context: Context) : RecyclerView.ViewHolder(binding.root){
        fun bind(studyData: StudyDTO) = with(binding){
            //  틀 내용 채우기
            Glide.with(context).load(studyData.imgUrl).into(iv!!)
            title.text = studyData.title
            description.text = studyData.description
        }
    }

    fun setItems(newItems : LiveData<ArrayList<StudyDTO>>){
        items = newItems
    }

}