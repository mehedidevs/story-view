package com.example.my_stories.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.example.my_stories.R
import com.example.my_stories.stories.StoriesProgressView

class MainActivityDemo : AppCompatActivity(), StoriesProgressView.StoriesListener {


    private var storiesProgressView: StoriesProgressView? = null
    private var image: ImageView? = null

    private var counter = 0
    private val resources = intArrayOf(
        R.drawable.sample1,
        R.drawable.sample2,
        R.drawable.sample3,
        R.drawable.sample4,
        R.drawable.sample5,
        R.drawable.sample6
    )

    private val durations = longArrayOf(500L, 1000L, 1500L, 4000L, 5000L, 1000)

    private var pressTime = 0L
    private var limit = 500L


    private val onTouchListener = View.OnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                pressTime = System.currentTimeMillis()
                storiesProgressView?.pause()
                return@OnTouchListener false
            }

            MotionEvent.ACTION_UP -> {
                val now = System.currentTimeMillis()
                storiesProgressView?.resume()
                return@OnTouchListener limit < now - pressTime
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storiesProgressView?.setStoriesCount(PROGRESS_COUNT)
        storiesProgressView?.setStoryDuration(3000L)
        // or
         storiesProgressView?.setStoriesCountWithDurations(durations);

        storiesProgressView?.setStoriesListener(this)

        counter = 2
        storiesProgressView?.startStories(counter)

//        image = findViewById<ImageView>(R.id.image)
//        image?.setImageResource(resources[counter])

        // bind reverse view
        val reverse = findViewById<View>(R.id.reverse)
        reverse.setOnClickListener { storiesProgressView?.reverse() }
        reverse.setOnTouchListener(onTouchListener)

        // bind skip view
        val skip = findViewById<View>(R.id.skip)
        skip.setOnClickListener { storiesProgressView?.skip() }
        skip.setOnTouchListener(onTouchListener)
    }


    override fun onNext() {
        image?.setImageResource(resources[++counter])

        Log.d("TAG", "onNext: ")
    }

    override fun onPrev() {
        if (counter - 1 < 0) return
        image?.setImageResource(resources[--counter])


        Log.d("TAG", "onPrev: ")
    }

    override fun onComplete() {}

    override fun onDestroy() {
        // Very important !
        storiesProgressView?.destroy()
        super.onDestroy()
    }

    companion object {
        private const val PROGRESS_COUNT = 6
    }
}