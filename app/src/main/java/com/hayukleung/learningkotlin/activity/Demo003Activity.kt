package com.hayukleung.learningkotlin.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.hayukleung.learningkotlin.R
import com.hayukleung.learningkotlin.adapter.Demo003Adapter
import com.hayukleung.learningkotlin.databinding.ActivityDemo003Binding
import com.hayukleung.learningkotlin.javabean.User
import java.util.*

/**
 * LearningKotlin
 *
 * liangxiaxu@aobi.com
 * 2017-12-12 11:04
 */
class Demo003Activity : AppCompatActivity() {

    val userList: MutableList<User> = ArrayList()
    var binding: ActivityDemo003Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_demo_003)

        binding?.let {
            it.recyclerView.adapter = Demo003Adapter(this, userList)
            it.recyclerView.layoutManager = LinearLayoutManager(this)
        }

        binding?.let {
            it.button.setOnClickListener {
                refresh()
            }
        }
    }

    private fun refresh() {
        userList.clear()

        (0 until 100)
                .map { User(it, String.format(Locale.CHINA, "%d", it)) }
                .forEach { userList.add(it) }

        binding?.let { it.recyclerView.adapter.notifyDataSetChanged() }
    }
}