package com.example.deswita.utils

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import coil.load
import com.example.deswita.databinding.FragmentImageBinding


class ImageFragment : DialogFragment() {

    private lateinit var _binding: FragmentImageBinding
    private val binding get() = _binding
    private lateinit var imageString: String

    companion object {
        const val IMAGE_STR = "IMAGE_STR"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageBinding.inflate(inflater,container,false)

        imageString = arguments?.getString(IMAGE_STR,"") as String

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog!!.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            dialog!!.getWindow()!!.setLayout(width, height)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClose.setOnClickListener {
            dismiss()
        }

        binding.imageView.load(Utils.getImageDrawable(requireActivity(),imageString))
    }

}