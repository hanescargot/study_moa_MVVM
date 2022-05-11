package com.pyrion.studymoa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pyrion.studymoa.databinding.ItemStudyBinding
import com.pyrion.studymoa.utils.StudyDTO


class BottomSheetRecyclerViewAdapter(
    private var items: LiveData<ArrayList<StudyDTO>>
    ) : RecyclerView.Adapter<BottomSheetRecyclerViewAdapter.RecyclerViewHolder>() {
    var _items = items.value

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ItemStudyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        _items?.get(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return _items?.size!!
    }


    inner class RecyclerViewHolder (private val binding: ItemStudyBinding, val context: Context) : RecyclerView.ViewHolder(binding.root){
        fun bind(studyData: StudyDTO) = with(binding){
            //  틀 내용 채우기
            Glide.with(context).load(studyData.imgUrl).into(iv!!)
            title.text = studyData.title
            description.text = studyData.description
            bg.setOnClickListener(){
                itemClickListener.onRecyclerItemClick(studyData)
            }
        }
    }

    fun setItems(newItems : ArrayList<StudyDTO>){
        _items = newItems
    }


    lateinit var  itemClickListener : OnRecyclerItemClickListener;

    public interface OnRecyclerItemClickListener {
        fun onRecyclerItemClick( studyDto:StudyDTO )

    }
    fun setOnItemClickListener(itemClickListener: OnRecyclerItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}