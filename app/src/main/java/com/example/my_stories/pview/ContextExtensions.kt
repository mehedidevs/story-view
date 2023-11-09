package com.example.my_stories.pview

import android.content.Context

fun Context.dp(valueInDp: Int): Int = (valueInDp * resources.displayMetrics.density).toInt()
