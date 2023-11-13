package com.example.my_stories.story_rcv

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.my_stories.OnboardingAdapter
import com.example.my_stories.databinding.RowItemBinding
import com.example.my_stories.databinding.RowPagerItemBinding


class InnerAdapterPagerLast(private var imageList: List<String>) :
    RecyclerView.Adapter<InnerAdapterPagerLast.ViewHolder>() {

    lateinit var context: Context

    class ViewHolder(val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.apply {

                Glide.with(context).load(imageList[position]).into(holder.binding.imagePager)


            }
        }
    }

//    fun updateData(newList: List<Int>) {
//        imageList = newList
//        notifyDataSetChanged()
//    }

    override fun getItemCount() = imageList.size
}