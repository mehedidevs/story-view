package com.example.my_stories

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.my_stories.databinding.ActivityMainBinding
import com.example.my_stories.demo.ProgressDrawable
import com.example.my_stories.demo.nextPage
import com.example.my_stories.demo.previousPage
import com.example.my_stories.stories.StoriesProgressView
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.abs


class MainActivity : AppCompatActivity() {


    private var storiesProgressView: StoriesProgressView? = null
    private var image: ImageView? = null

    private var counter = 0


    private val resources1 = listOf<Int>(
        R.drawable.home_ad,
        R.drawable.home_ad1,
        R.drawable.home_ad2,
        R.drawable.home_ad3,
        R.drawable.home_ad4
    )
    private val resources2 = listOf<Int>(
        R.drawable.sample1,
        R.drawable.sample2,
        R.drawable.sample3,
        R.drawable.sample4,
        R.drawable.sample5,
        R.drawable.sample6
    )

    val storyList = listOf<List<Int>>(resources1, resources2)

    private val durations = longArrayOf(500L, 1000L, 1500L, 4000L, 5000L, 1000)

    private var pressTime = 0L
    private var limit = 500L
    private val sliderHandler = Handler()
    lateinit var adapter: OnboardingAdapter

    lateinit var binding: ActivityMainBinding
    var currentIndex = 0
    var hasMoreStory = true

    private val sliderRunnable =
        Runnable { binding.introViewPager.currentItem = binding.introViewPager.currentItem + 1 }
    val hasNext = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        setPagerData(storyList[0])


    }

    private fun setPagerData(story: List<Int>) {

        adapter = OnboardingAdapter(story)
        binding.introViewPager.adapter = adapter

        binding.introViewPager.clipToPadding = false
        binding.introViewPager.clipChildren = false
//        binding.introViewPager.offscreenPageLimit = 3
        // binding.introViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS

//        val compositePageTransformer = CompositePageTransformer()
//        compositePageTransformer.addTransformer(MarginPageTransformer(400))
//        compositePageTransformer.addTransformer { page, position ->
//            val r = 1f
//            abs(position)
//            page.scaleY = 0.85f + r * 0.15f
//        }
//        val d = ProgressDrawable(adapter.itemCount, -0x22ff0100, 0x4400ff00)
//        binding.progressHorizontal.progressDrawable = d

        binding.segmentedProgressbar.setSegmentCount(adapter.itemCount)

        //  binding.introViewPager.setPageTransformer(compositePageTransformer)
        binding.introViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                //  binding.segmentedProgressbar.playSegment(2000)
                binding.segmentedProgressbar.setCompletedSegments(position + 1)


                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 5000) // slide duration 2 seconds

                Log.d("TAG", "onPageSelected: $position")

                if (position == (binding.introViewPager.adapter?.itemCount?.minus(1))) {

                   // setPagerData(storyList[1])
                } else {

                }

//
//                if (position == storyList.size - 1) {
//                    if (storyList.size - 1 >= currentIndex) {
//                        currentIndex++
//                        setPagerData(storyList[currentIndex])
//                    }
//                }
            }
        })



        binding.reverse.setOnClickListener {
            Log.d("TAG", "reverse: ")
            binding.introViewPager.previousPage()
        }

        binding.skip.setOnClickListener {
            Log.d("TAG", "Next: ")

            binding.introViewPager.nextPage()


        }


    }


    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 5000)
    }
}

