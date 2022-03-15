package com.example.deswita.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.deswita.R
import com.example.deswita.databinding.FragmentChangePassword2Binding
import com.example.deswita.databinding.FragmentChangePassword3Binding
import com.example.deswita.ui.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ChangePassword3Fragment : BottomSheetDialogFragment() {

    private var _binding: FragmentChangePassword3Binding? = null
    private val binding get() = _binding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangePassword3Binding.inflate(inflater,container,false)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.btnSave?.setOnClickListener {
            val password = binding?.editTextPassword?.text?.trim().toString()
            val password2 = binding?.editTextPassword2?.text?.trim().toString()

            when {
                password.isEmpty() -> {
                    binding?.editTextPassword?.error = "Field required"
                }
                password2.isEmpty() -> {
                    binding?.editTextPassword2?.error = "Field required"
                }
                password != password2 -> {
                    binding?.editTextPassword2?.error = "Password must be same"
                }
                else -> {

                    mainViewModel.password = password

                    val successFragment = SuccessFragment()
                    successFragment.show(parentFragmentManager,SuccessFragment::class.java.simpleName)
                    dismiss()
                }
            }

        }
    }

}