package com.hayukleung.learningkotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hayukleung.learningkotlin.databinding.ItemDemo003Binding
import com.hayukleung.learningkotlin.functionextension.toast
import com.hayukleung.learningkotlin.javabean.User

/**
 * LearningKotlin
 *
 * liangxiaxu@aobi.com
 * 2017-12-12 11:24
 */
class Demo003Adapter(private val context: Context, private val userList: List<User>) : RecyclerView.Adapter<Demo003Adapter.Demo003ViewHolder>() {

    override fun onBindViewHolder(holder: Demo003ViewHolder?, position: Int) {
        with(holder?.itemView!!) {
            holder.bindData(userList[position])
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Demo003ViewHolder {
        val binding: ItemDemo003Binding = ItemDemo003Binding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return Demo003ViewHolder(context, binding)
    }

    class Demo003ViewHolder(private val context: Context, val binding: ItemDemo003Binding?) : RecyclerView.ViewHolder(binding?.root) {

        fun bindData(user : User) {
            binding?.name?.text = user.name
            binding?.item?.setOnClickListener {
                context.toast(user.name)
            }
        }
    }
}