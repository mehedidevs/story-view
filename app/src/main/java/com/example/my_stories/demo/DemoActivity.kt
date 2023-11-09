//package com.example.my_stories.demo
//
//import android.os.Bundle
//import android.os.Handler
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.RecyclerView
//import androidx.viewpager2.widget.CompositePageTransformer
//import androidx.viewpager2.widget.MarginPageTransformer
//import androidx.viewpager2.widget.ViewPager2
//import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
//import com.example.my_stories.R
//
//class MainActivity : AppCompatActivity() {
//    private var viewPager2: ViewPager2? = null
//    private val sliderHandler = Handler()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        viewPager2 = findViewById<ViewPager2>(R.id.viewPagerImageSlider)
//        val sliderItems: MutableList<SliderItems> = ArrayList<SliderItems>()
//        sliderItems.add(SliderItems(R.drawable.image1))
//        sliderItems.add(SliderItems(R.drawable.image2))
//        sliderItems.add(SliderItems(R.drawable.image3))
//        sliderItems.add(SliderItems(R.drawable.image4))
//
//        sliderItems.add(SliderItems(R.drawable.image5))
//        viewPager2.setAdapter(SliderAdapter(sliderItems, viewPager2))
//        viewPager2.setClipToPadding(false)
//        viewPager2.setClipChildren(false)
//        viewPager2.setOffscreenPageLimit(3)
//        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
//        val compositePageTransformer = CompositePageTransformer()
//        compositePageTransformer.addTransformer(MarginPageTransformer(40))
//        compositePageTransformer.addTransformer { page, position ->
//            val r = 1f
//            Math.abs(position)
//            page.scaleY = 0.85f + r * 0.15f
//        }
//        viewPager2.setPageTransformer(compositePageTransformer)
//        viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                sliderHandler.removeCallbacks(sliderRunnable)
//                sliderHandler.postDelayed(sliderRunnable, 2000) // slide duration 2 seconds
//            }
//        })
//    }
//
//    private val sliderRunnable =
//        Runnable { viewPager2!!.currentItem = viewPager2!!.currentItem + 1 }
//
//    override fun onPause() {
//        super.onPause()
//        sliderHandler.removeCallbacks(sliderRunnable)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        sliderHandler.postDelayed(sliderRunnable, 2000)
//    }
//}