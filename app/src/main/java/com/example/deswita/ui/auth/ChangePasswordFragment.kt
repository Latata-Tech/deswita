package com.example.deswita.ui.auth

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.deswita.R
import com.example.deswita.databinding.FragmentChangePasswordBinding
import com.example.deswita.service.OtpReceiver
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSend.setOnClickListener {
            if(binding.editTextEmail.text.trim().toString().isEmpty()) {
                binding.editTextEmail.error = "Field Required"
            }else {

                binding.btnSend.visibility = View.GONE
                binding.tvSendOtp.visibility = View.VISIBLE

            }
        }

    }


}