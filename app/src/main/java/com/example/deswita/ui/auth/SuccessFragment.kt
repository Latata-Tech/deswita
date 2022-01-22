package com.example.deswita.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deswita.R
import com.example.deswita.databinding.FragmentSuccessBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SuccessFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSuccessBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuccessBinding.inflate(inflater,container,false)


        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnLogin?.setOnClickListener {
            dismiss()
        }
    }

}