package com.hayukleung.learningkotlin.functionextension

import android.content.Context
import android.widget.Toast
import java.util.*

/**
 * 函数拓展
 *
 * LearningKotlin
 *
 * liangxiaxu@aobi.com
 * 2017-12-11 18:07
 */
fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun format(message: Int): String {
    return String.format(Locale.CHINA, "%d", message)
}

fun format(message: Long): String {
    return String.format(Locale.CHINA, "%d", message)
}