package com.example.deswita.ui.mainmenu.story

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deswita.R
import com.example.deswita.databinding.FragmentStoryBinding
import com.example.deswita.models.Comment
import com.example.deswita.models.Story
import com.example.deswita.ui.MainViewModel
import com.example.deswita.ui.mainmenu.story.adapters.StoryAdapter
import com.example.deswita.ui.story.StoryActivity
import com.example.deswita.utils.Firestore
import com.example.deswita.utils.ImageFragment
import com.example.deswita.utils.Storage
import com.google.android.material.appbar.AppBarLayout
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import org.json.JSONObject
import org.json.JSONTokener

class StoryFragment : Fragment() {

    private var _binding: FragmentStoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var  mainViewModel: MainViewModel
    private lateinit var firestoreDB: FirebaseFirestore
    private lateinit var storageDB: FirebaseStorage

    private lateinit var storyAdapter: StoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoryBinding.inflate(inflater,container,false)

        firestoreDB = Firestore.db
        storageDB = Storage.storage

        val findViewById = activity?.findViewById<AppBarLayout>(R.id.appBarLayoutMain)
        findViewById?.visibility = View.VISIBLE

        storyAdapter = StoryAdapter(requireActivity())
        initialRecycler()

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        binding.fabStory.setOnClickListener {
            startActivity(Intent(requireContext(),AddStoryActivity::class.java))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        storyAdapter.setData(mainViewModel.storiesDummy)
        firestoreDB.collection("stories").orderBy("date",Query.Direction.DESCENDING).addSnapshotListener{ snapshot, e ->
            val stories = ArrayList<Story>()
            snapshot?.forEach { item ->
                val data = item.data
                val name = data.getValue("name").toString()
                val profile = data.getValue("profile").toString()
                val likeTotal = data.getValue("likeTotal").toString().toInt()
                val id = data.getValue("id").toString().toInt()
                val description = data.getValue("description").toString()
                val contentText = data.getValue("contentText").toString()
                val contentImage = data.getValue("contentImage").toString()
                val comments = data.getValue("comments").toString().toInt()
                val commentTotal = data.getValue("commentTotal").toString().toInt()
                val story = Story(id,name,description,contentText,contentImage,profile,likeTotal,commentTotal,comments)
                stories.add(story)
            }
            storyAdapter.setData(stories)
        }

    }

    fun initialRecycler(){
        binding.rvStory.layoutManager = LinearLayoutManager(requireContext())
        binding.rvStory.setHasFixedSize(true)
        binding.rvStory.adapter = storyAdapter

        storyAdapter.setOnItemClickCallback(object: StoryAdapter.OnItemClickCallback{
            override fun onClick(story: Story,state: String) {

                when {
                    state == StoryAdapter.CLICK_IMAGE -> {
                        val bundle = Bundle()
                        bundle.putString(ImageFragment.IMAGE_STR,story.contentImage)
                        val imageFragment = ImageFragment()
                        imageFragment.arguments = bundle
                        imageFragment.show(childFragmentManager, ImageFragment::class.java.simpleName)
                    }
                    state == StoryAdapter.CLICK_COMMENT -> {
                        val intent = Intent(requireContext(),StoryActivity::class.java)
                        intent.putExtra(StoryActivity.EXTRA_STORY,story)
                        startActivity(intent)
                    }
                    state == StoryAdapter.CLICK_USER -> {
                        Toast.makeText(requireContext(),"${story.name} user",Toast.LENGTH_SHORT).show()
                    }
                }

            }
        })
    }



}