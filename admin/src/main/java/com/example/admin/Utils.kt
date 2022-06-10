package com.example.admin

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager

object Utils {

    fun getImageDrawable(context: Context,name: String) : Drawable {
        val resources: Resources = context.resources
        val resourceId: Int = resources.getIdentifier(
            name, "drawable",
            context.packageName
        )
        return resources.getDrawable(resourceId) as Drawable
    }

    fun hideSoftKeyboard(act: Activity) {
        val imm = act.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = act.currentFocus
        if (view == null) {
            view = View(act)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}