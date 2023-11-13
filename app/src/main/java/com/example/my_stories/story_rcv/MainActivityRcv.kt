package com.example.my_stories.story_rcv

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_stories.OnboardingAdapter

import com.example.my_stories.databinding.ActivityMainRcvBinding
import com.example.my_stories.databinding.ItemStoryBinding
import com.example.my_stories.storyview.StoryView
import com.example.my_stories.storyview.callback.StoryClickListeners
import com.example.my_stories.storyview.model.MyStory


data class Story(val username: String, val stories: ArrayList<String>)

class MainActivityRcv : AppCompatActivity() {


    // Example: Load stories into a RecyclerView
    val user1Stories = arrayListOf(

        "https://media.macphun.com/img/uploads/customer/how-to/608/15542038745ca344e267fb80.28757312.jpg",

        "https://media.macphun.com/img/uploads/customer/how-to/608/15542038745ca344e267fb80.28757312.jpg",

        "https://media.macphun.com/img/uploads/customer/how-to/608/15542038745ca344e267fb80.28757312.jpg",


        // Add more story images or videos as needed
    )

    val user2Stories = arrayListOf(

        "https://www.befunky.com/images/prismic/82e0e255-17f9-41e0-85f1-210163b0ea34_hero-blur-image-3.jpg",

        "https://www.freecodecamp.org/news/content/images/2022/09/jonatan-pie-3l3RwQdHRHg-unsplash.jpg",

        "https://www.freecodecamp.org/news/content/images/2022/09/jonatan-pie-3l3RwQdHRHg-unsplash.jpg",
        // Add more story images or videos as needed
    )

    val stories = listOf(
        Story("user1", user1Stories),
        Story("user2", user2Stories),
        // Add more users with their stories as needed
    )

    lateinit var binding: ActivityMainRcvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainRcvBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = StoryAdapter(stories, supportFragmentManager, this)
        binding.introViewPager.adapter = adapter

    }
}

class StoryAdapter(
    private val stories: List<Story>,
    val fragmentManager: FragmentManager,
    val context: Context
) :
    RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {


    private val sliderHandler = Handler()
    lateinit var adapter: OnboardingAdapter

    private val sliderRunnable: Runnable? = null

    val hasNext = true
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {

        return StoryViewHolder(
            ItemStoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = stories[position]
        holder.binding.storyUsername.text = story.username
        // For simplicity, only display the first story image as a thumbnail
        if (story.stories.isNotEmpty()) {

            holder.binding.imageSliderRcv.adapter = InnerAdapterPagerLast(story.stories)


        }


    }

    override fun getItemCount(): Int {
        return stories.size
    }


    inner class StoryViewHolder(val binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
//        fun bind(story: Story) {
//
//            itemView.storyUsername.text = story.username
//            // For simplicity, only display the first story image as a thumbnail
//            if (story.stories.isNotEmpty()) {
//                itemView.storyImage.setImageResource(story.stories[0])
//            }
//            // Add click listeners or other interactions as needed
//        }
    }
}

//private fun showStories(myStories: ArrayList<MyStory>) {
//
//
//    StoryView.Builder(fragmentManager)
//        .setStoriesList(myStories)
//        .setStoryDuration(2000)
//        .setStoryClickListeners(object : StoryClickListeners {
//            override fun onDescriptionClickListener(position: Int) {
//                Toast.makeText(
//                    context,
//                    "Clicked: " + myStories[position].description,
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//
//            override fun onTitleIconClickListener(position: Int) {}
//        })
//        .setOnStoryChangedCallback { position ->
//            Toast.makeText(
//                context,
//                position.toString() + "",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//        .setStartingIndex(0)
//
//        .build()
//        .show()
//}
