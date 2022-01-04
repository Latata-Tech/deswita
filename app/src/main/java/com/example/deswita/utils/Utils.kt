package com.example.deswita.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable

object Utils {

    fun getImageDrawable(context: Context,name: String) : Drawable {
        val resources: Resources = context.resources
        val resourceId: Int = resources.getIdentifier(
            name, "drawable",
            context.packageName
        )
        return resources.getDrawable(resourceId) as Drawable
    }

}