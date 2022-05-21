package com.pyrion.studymoa.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.pyrion.studymoa.databinding.ItemMyStudyBinding
import com.pyrion.studymoa.utils.MyStudyDTO
import com.pyrion.studymoa.utils.StudyDTO


class MyStudyListViewAdapter(
    val context: Context,
    private var items: LiveData<ArrayList<MyStudyDTO>>
    ) : BaseAdapter(){
    override fun getCount(): Int {
       return items.value?.size!!
    }

    override fun getItem(i: Int): Any {
        return items.value?.get(i)!!
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(i: Int, convertView: View?, parent: ViewGroup?): View {
        val item : MyStudyDTO? = items.value?.get(i)
        var binding = ItemMyStudyBinding.inflate(LayoutInflater.from(context))
        Glide.with(context).load(item?.imgUrl).into(binding.iv);
        binding.title.text = item?.title
        binding.description.text = item?.description
        binding.delete.setOnClickListener{
            deleteButtonClickListener.onClickDelete(context)
        }
        binding.edit.setOnClickListener(){editButtonClickListener.onClickEdit(context, item!!)}
        return binding.root
    }

    lateinit var editButtonClickListener: OnEditButtonClickListener
    interface OnEditButtonClickListener {
        fun onClickEdit(context: Context, studyDTO: MyStudyDTO)//기본 선언된 내용이 없을 때
    }
    fun setOnEditButtonClickListener(listener: OnEditButtonClickListener){
        editButtonClickListener = listener
    }




    lateinit var  deleteButtonClickListener : OnDeleteButtonClickListener
    interface OnDeleteButtonClickListener {
        fun onClickDelete(context: Context){
            //기본 선언된 내용이 있을 때
        }
    }
    fun setOnDeleteButtonClickListener(listener: OnDeleteButtonClickListener){
        deleteButtonClickListener = listener
    }

}