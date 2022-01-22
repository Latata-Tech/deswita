package com.example.deswita.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.example.deswita.R

class OtpTextChange (
    private val view: View,
    private val editText: Array<EditText>,
    val listener: Listener
 ) : TextWatcher {

    override fun afterTextChanged(editable: Editable) {
        val text = editable.toString()
        when (view.id) {
            R.id.et_otp1 -> if (text.length == 1) editText[1].requestFocus()
            R.id.et_otp2 -> if (text.length == 1) editText[2].requestFocus()
            R.id.et_otp3 -> if (text.length == 1) editText[3].requestFocus()
        }
        listener.onTextFull(
            editText[0].text.isNotEmpty() && editText[1].text.isNotEmpty()
                    && editText[2].text.isNotEmpty() && editText[3].text.isNotEmpty()
        )
    }

    override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
    override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}

    interface Listener {
        fun onTextFull(isActive: Boolean)
    }

}