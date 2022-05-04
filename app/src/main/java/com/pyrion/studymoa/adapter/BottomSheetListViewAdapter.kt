package com.pyrion.studymoa.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bumptech.glide.Glide
import com.pyrion.studymoa.databinding.ItemStudyBinding
import com.pyrion.studymoa.utils.StudyDTO
import com.pyrion.studymoa.view.MainActivity


class BottomSheetListViewAdapter(private val context:Context) : BaseAdapter() {
    lateinit var liveData : LiveData<ArrayList<StudyDTO>>
    lateinit var dataList : LiveData<ArrayList<StudyDTO>>

    override fun getCount(): Int {
        return dataList.value?.size!!
    }

    override fun getItem(position: Int): Any {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        lateinit var binding: ItemStudyBinding
        val view : View
        val holder : viewHolder
        if(convertView == null){//View Holder 를 사용해서 틀을 재사용 함으로써 반복 생성 하지 않도록 체크 -> 스크롤링 성능 향상
            // 틀 새로 생성
            binding = ItemStudyBinding.inflate(LayoutInflater.from(context))
            view = binding.root

            holder = viewHolder()
            holder.iv = binding.iv
            holder.title = binding.title
            holder.message = binding.message

            view.tag = holder  // view의 tag에 holder(틀)을 기록
        }
        else{
            // 틀 재사용
            view = convertView
            holder = view.tag as viewHolder
        }

        // 틀 내용 채우기
        val study = dataList[position]
        holder.title?.text = study.title
        holder.message?.text = study.message
        Glide.with(context).load(study.imgUrl).into(holder.iv!!)

        return view
    }

    private class viewHolder (var iv : ImageView?=null, var title : TextView?=null, var message : TextView?=null)



}