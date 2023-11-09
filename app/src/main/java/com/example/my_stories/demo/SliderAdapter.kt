//package com.example.my_stories.demo
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import androidx.viewpager2.widget.ViewPager2
//import com.example.my_stories.demo.SliderAdapter.SliderViewHolder
//
//class SliderAdapter internal constructor(sliderItems: List<SliderItems>, viewPager2: ViewPager2) :
//    RecyclerView.Adapter<SliderViewHolder>() {
//    private val sliderItems: List<SliderItems>
//    private val viewPager2: ViewPager2
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
//        return SliderViewHolder(
//            LayoutInflater.from(parent.context).inflate(
//                R.layout.slide_item_container, parent, false
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
//        holder.setImage(sliderItems[position])
//        if (position == sliderItems.size() - 2) {
//            viewPager2.post(runnable)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return sliderItems.size()
//    }
//
//    internal inner class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val imageView: RoundedImageView
//
//        init {
//            imageView = itemView.findViewById(R.id.imageSlide)
//        }
//
//        fun setImage(sliderItems: SliderItems) {
//
////use glide or picasso in case you get image from internet
//            imageView.setImageResource(sliderItems.getImage())
//        }
//    }
//
//    private val runnable = Runnable {
//        sliderItems.addAll(sliderItems)
//        notifyDataSetChanged()
//    }
//
//    init {
//        this.sliderItems = sliderItems
//        this.viewPager2 = viewPager2
//    }
//}