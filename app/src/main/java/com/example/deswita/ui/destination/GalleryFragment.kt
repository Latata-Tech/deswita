package com.example.deswita.ui.destination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.deswita.R
import com.example.deswita.databinding.FragmentGalleryBinding
import com.example.deswita.ui.MainViewModel
import com.example.deswita.ui.destination.adapters.GaleryAdapter
import com.example.deswita.utils.ImageFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class GalleryFragment : BottomSheetDialogFragment() {

    private lateinit var _binding: FragmentGalleryBinding
    private val binding get() = _binding
    private lateinit var adapter: GaleryAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater,container,false)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        adapter = GaleryAdapter(requireActivity())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvGallery.layoutManager = GridLayoutManager(context,3)
        binding.rvGallery.setHasFixedSize(true)
        binding.rvGallery.adapter = adapter

        adapter.setData(mainViewModel.getGallery() + mainViewModel.getGallery())

        adapter.setOnItemClickCallback(object: GaleryAdapter.OnItemClickCcallback{
            override fun onClick(photo: String) {
                val bundle = Bundle()
                bundle.putString(ImageFragment.IMAGE_STR,photo)
                val imageFragment = ImageFragment()
                imageFragment.arguments = bundle
                imageFragment.show(parentFragmentManager, ImageFragment::class.java.simpleName)
            }
        })

    }
}