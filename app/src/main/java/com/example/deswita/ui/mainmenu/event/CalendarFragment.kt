package com.example.deswita.ui.mainmenu.event

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.deswita.databinding.FragmentCalendarBinding
import com.example.deswita.ui.MainViewModel
import java.text.SimpleDateFormat

class CalendarFragment : DialogFragment() {

    private lateinit var _binding: FragmentCalendarBinding
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding  = FragmentCalendarBinding.inflate(LayoutInflater.from(inflater.context),container,false)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null) {
            val display = DisplayMetrics()
            requireActivity().windowManager.defaultDisplay.getMetrics(display)
            val width = display.widthPixels - 30
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog?.window?.setLayout(width, height)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

//      set chosen date to calendar view
        binding.calendar.setDate(mainViewModel.getDate())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = SimpleDateFormat("dd-MM-yyyy").parse("$dayOfMonth-${month + 1}-$year")
            mainViewModel.setDate(date)
            dialog?.dismiss()
        }

    }



}