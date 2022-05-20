package com.example.deswita.ui.mainmenu.search

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.example.deswita.R
import com.example.deswita.databinding.ActivityMainBinding
import com.example.deswita.databinding.ActivitySearchBinding
import com.example.deswita.utils.SharePrefHelper

class SearchActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchBinding
    private lateinit var fragmentImplement : Fragment;

    private val HISTORY_SEARCH_FILE = "HISTORY_SEARCH_FILE"
    private lateinit var historyPreferences: SharePrefHelper
    private var historyList: ArrayList<String>? = null

    companion object {
        const val HISTORY_LIST = "HISTORY_LIST"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        historyPreferences = SharePrefHelper(this,HISTORY_SEARCH_FILE)
        val set = historyPreferences.histories?.toMutableList()

        fragmentImplement = SearchHistoryFragment()

        if(!set.isNullOrEmpty()) {
            val historyBundle = Bundle()
            historyBundle.putStringArrayList(HISTORY_LIST,set as ArrayList<String>)
            fragmentImplement.arguments = historyBundle

            supportFragmentManager.beginTransaction().apply {
                add(R.id.frameContainerHistory, fragmentImplement, SearchHistoryFragment::class.java.simpleName)
                commit()
            }
        }



        binding.backSearchBtn.setOnClickListener {
            finish()
        }

        binding.textInputEditText.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_SEARCH){
                findDestination()

                val search = HashSet<String>()
                search.add(textView.text.toString())
                val newSet = if(historyPreferences.histories.isNullOrEmpty()) search else historyPreferences.histories!! + search
                historyPreferences.histories = newSet

                val historyBundle = Bundle()
                historyBundle.putStringArrayList(HISTORY_LIST,newSet.toMutableList() as ArrayList<String>)

                val fragmentHistory = SearchHistoryFragment()
                fragmentHistory.arguments = historyBundle
                replaceFragmentHistory(fragmentHistory, fragmentHistory::class.java.simpleName)
            }
            return@setOnEditorActionListener true
        }
    }

    protected fun findDestination()
    {
        fragmentImplement = SearchRecomendationFragment()
        replaceFragmentSearch(fragmentImplement, fragmentImplement::class.java.simpleName)
    }


    protected fun replaceFragmentSearch(fragment : Fragment, tag : String) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameContainerSearch, fragment, tag)
            commit()
        }
    }

    protected fun replaceFragmentHistory(fragment : Fragment, tag : String) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameContainerHistory, fragment, tag)
            commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, "SEARCH_RECOMENDATION", fragmentImplement)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        fragmentImplement = supportFragmentManager.getFragment(savedInstanceState, "SEARCH_RECOMENDATION")!!
        replaceFragmentSearch(fragmentImplement, fragmentImplement::class.java.simpleName)
    }
}