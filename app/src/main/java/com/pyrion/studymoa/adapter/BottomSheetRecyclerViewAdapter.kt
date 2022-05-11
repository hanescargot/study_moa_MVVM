package com.pyrion.studymoa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pyrion.studymoa.databinding.ItemStudyBinding
import com.pyrion.studymoa.utils.StudyDTO


class BottomSheetRecyclerViewAdapter(
    private var items: LiveData<ArrayList<StudyDTO>>, OnRecyclerItemClickListener onRecyclerItemClickListener
    ) : RecyclerView.Adapter<BottomSheetRecyclerViewAdapter.RecyclerViewHoler>() {
    var _items = items.value

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHoler {
        val binding = ItemStudyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHoler(binding, parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerViewHoler, position: Int) {
        _items?.get(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return _items?.size!!
    }

    inner class RecyclerViewHoler (private val binding: ItemStudyBinding, val context: Context) : RecyclerView.ViewHolder(binding.root){
        fun bind(studyData: StudyDTO) = with(binding){
            //  틀 내용 채우기
            Glide.with(context).load(studyData.imgUrl).into(iv!!)
            title.text = studyData.title
            description.text = studyData.description
            bg.setOnClickListener {  }
        }
    }

    fun setItems(newItems : ArrayList<StudyDTO>){
        _items = newItems
    }

    public interface OnRecyclerItemClickListener {
        fun onRecyclerItemClick( studyDto:StudyDTO )

    }

}