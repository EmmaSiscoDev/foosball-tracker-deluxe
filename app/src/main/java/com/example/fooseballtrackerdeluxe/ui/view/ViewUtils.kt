package com.example.fooseballtrackerdeluxe.ui.view

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.icu.text.ScientificNumberFormatter
import androidx.core.content.ContextCompat
import com.example.fooseballtrackerdeluxe.R
import java.text.NumberFormat

fun getRectangleBackground(context: Context): GradientDrawable {
    return GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        setStroke(1, ContextCompat.getColor(context, R.color.colorPrimaryDark))
    }
}
//Use this for views
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Double.percent: String
    get() = NumberFormat.getPercentInstance().format(this)