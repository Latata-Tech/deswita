package com.example.deswita.ui.mainmenu.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deswita.R

class HomeViewModel: ViewModel() {

    val filterIds = arrayListOf(R.id.chip_filter_all,R.id.chip_filter_recommended,R.id.chip_filter_popular,R.id.chip_filter_rating,R.id.chip_filter_favorite)
    private var _activeFilter = MutableLiveData<Int>()
    val activeFilter: LiveData<Int> get() = _activeFilter


    init {
        setActiveFilter(R.id.chip_filter_all)
    }

    fun setActiveFilter(id: Int) {
        _activeFilter.postValue(id)
    }

}