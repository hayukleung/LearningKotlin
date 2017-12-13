package com.hayukleung.learningkotlin.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hayukleung.learningkotlin.R
import com.hayukleung.learningkotlin.databinding.ActivityDemo005Binding

/**
 * LearningKotlin
 *
 * liangxiaxu@aobi.com
 * 2017-12-13 14:58
 */
class Demo005Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDemo005Binding = DataBindingUtil.setContentView(this, R.layout.activity_demo_005)
    }
}