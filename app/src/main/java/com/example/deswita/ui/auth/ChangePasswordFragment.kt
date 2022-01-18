package com.example.deswita.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deswita.R
import com.example.deswita.databinding.FragmentChangePasswordBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChangePasswordFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentChangePasswordBinding.inflate(inflater,container,false)

        return binding.root
    }


}