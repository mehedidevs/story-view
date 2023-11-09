package com.example.my_stories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.my_stories.databinding.RowItemBinding


class OnboardingAdapter(private val imageList: List<Int>) :
    RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {
    class ViewHolder(val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.apply {
                imagePager.setImageResource(imageList[position])
            }
        }
    }

    override fun getItemCount() = imageList.size
}