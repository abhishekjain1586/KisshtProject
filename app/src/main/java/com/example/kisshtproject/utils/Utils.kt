package com.example.kisshtproject.utils

import android.content.res.Resources
import android.util.TypedValue

class Utils {

    companion object {
        fun convertDpToPx(resources: Resources, dp: Float): Float {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
        }
    }
}