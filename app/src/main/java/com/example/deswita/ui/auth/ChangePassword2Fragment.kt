package com.example.deswita.ui.auth

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.deswita.R
import com.example.deswita.databinding.FragmentChangePassword2Binding
import com.example.deswita.databinding.FragmentChangePasswordBinding
import com.example.deswita.utils.OtpTextChange
import com.example.deswita.utils.Utils.hideSoftKeyboard
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ChangePassword2Fragment : BottomSheetDialogFragment(), OtpTextChange.Listener {

    private var _binding: FragmentChangePassword2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangePassword2Binding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initOtp()

        binding.btnVerification.isEnabled = false

        binding.btnVerification.setOnClickListener {
            if(it.isEnabled) {

                val changePassword3Fragment = ChangePassword3Fragment()
                changePassword3Fragment.show(parentFragmentManager,ChangePassword3Fragment::class.java.simpleName)

                dismiss()
            }
        }
    }

    private fun initOtp() {
        binding.apply {
            val edit = arrayOf(etOtp1, etOtp2, etOtp3, etOtp4)
            etOtp1.addTextChangedListener(OtpTextChange(etOtp1, edit, this@ChangePassword2Fragment))
            etOtp2.addTextChangedListener(OtpTextChange(etOtp2, edit, this@ChangePassword2Fragment))
            etOtp3.addTextChangedListener(OtpTextChange(etOtp3, edit, this@ChangePassword2Fragment))
            etOtp4.addTextChangedListener(OtpTextChange(etOtp4, edit, this@ChangePassword2Fragment))
        }
    }

    override fun onTextFull(isActive: Boolean) {
        binding.apply {
            btnVerification.isEnabled = isActive
        }
    }


}