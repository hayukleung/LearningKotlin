package com.hayukleung.learningkotlin.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hayukleung.learningkotlin.R
import com.hayukleung.learningkotlin.databinding.ActivityDemo002Binding
import com.hayukleung.learningkotlin.functionextension.toast
import com.hayukleung.learningkotlin.javabean.User

/**
 * LearningKotlin
 *
 * liangxiaxu@aobi.com
 * 2017-12-11 17:28
 */
class Demo002Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding : ActivityDemo002Binding = DataBindingUtil.setContentView(this, R.layout.activity_demo_002)
        binding.button001.setOnClickListener {
            toast("This is Demo002Activity.")
        }
        binding.button002.setOnClickListener {
            val user = User(0, "Kotlin")
            Demo001Activity.startActivity(this, user)
        }
    }
}