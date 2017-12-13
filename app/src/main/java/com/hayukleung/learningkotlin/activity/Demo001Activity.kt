package com.hayukleung.learningkotlin.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hayukleung.learningkotlin.R
import com.hayukleung.learningkotlin.databinding.ActivityDemo001Binding
import com.hayukleung.learningkotlin.functionextension.toast
import com.hayukleung.learningkotlin.javabean.User

/**
 * LearningKotlin
 *
 * liangxiaxu@aobi.com
 * 2017-12-11 17:28
 */
class Demo001Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding : ActivityDemo001Binding = DataBindingUtil.setContentView(this, R.layout.activity_demo_001)

        val user = intent.getSerializableExtra(USER) as User
        toast(user.name)
    }

    companion object {
        val USER = "USER"
        fun startActivity(context: Context, user: User) {
            val intent = Intent(context, Demo001Activity::class.java)
            intent.putExtra(USER, user)
            context.startActivity(intent)
        }
    }
}