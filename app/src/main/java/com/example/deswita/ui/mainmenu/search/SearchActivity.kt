package com.example.deswita.ui.mainmenu.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.example.deswita.R
import com.example.deswita.databinding.ActivityMainBinding
import com.example.deswita.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchHistoryFragment = SearchHistoryFragment()
        supportFragmentManager.beginTransaction().apply {
            add(R.id.frameContainerSearch, searchHistoryFragment, SearchHistoryFragment::class.java.simpleName)
            commit()
        }

        binding.backSearchBtn.setOnClickListener {
            finish()
        }

        binding.textInputEditText.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_SEARCH){
                findDestination()
            }
            return@setOnEditorActionListener true
        }
    }

    protected fun findDestination()
    {
        val searchRecomendationFragment = SearchRecomendationFragment()
        Log.i("Replace", "Replace Fragment")
        replaceFragment(searchRecomendationFragment, SearchRecomendationFragment::class.java.simpleName)
    }


    protected fun replaceFragment(fragment : Fragment, tag : String) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameContainerSearch, fragment, tag)
            commit()
        }
    }
}