package com.example.deswita.ui.mainmenu.story

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deswita.R
import com.example.deswita.databinding.FragmentStoryBinding
import com.example.deswita.models.Story
import com.example.deswita.ui.MainViewModel
import com.example.deswita.ui.mainmenu.story.adapters.StoryAdapter
import com.example.deswita.ui.story.StoryActivity
import com.example.deswita.utils.ImageFragment
import com.google.android.material.appbar.AppBarLayout

class StoryFragment : Fragment() {

    private var _binding: FragmentStoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var  mainViewModel: MainViewModel

    private lateinit var storyAdapter: StoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoryBinding.inflate(inflater,container,false)

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
        storyAdapter.setData(mainViewModel.storiesDummy)
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