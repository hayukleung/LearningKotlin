package com.hayukleung.learningkotlin.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.hayukleung.learningkotlin.R
import com.hayukleung.learningkotlin.databinding.ActivityDemo006Binding
import com.hayukleung.learningkotlin.functionextension.toast
import com.hayukleung.learningkotlin.mvp.IUserView
import com.hayukleung.learningkotlin.mvp.UserPresenter

/**
 * LearningKotlin
 *
 * liangxiaxu@aobi.com
 * 2017-12-13 15:36
 */
class Demo006Activity : AppCompatActivity(), IUserView, View.OnClickListener {

    private var binding: ActivityDemo006Binding? = null
    private var presenter: UserPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_demo_006)
        binding?.buttonSave?.setOnClickListener(this)
        binding?.buttonLoad?.setOnClickListener(this)
        presenter = UserPresenter(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.button_save -> presenter?.save(getId(), getName())
            R.id.button_load -> presenter?.load(getId())
        }
    }

    override fun getId(): Int {
        val id = binding?.editTextId?.text.toString().trim()
        return id.toInt()
    }

    override fun getName(): String {
        val name = binding?.editTextName?.text.toString().trim()
        return name
    }

    override fun setId(id: Int?) {
        if (id != null) {
            binding?.editTextId?.setText(id.toString())
        } else {
            binding?.editTextId?.setText(null)
        }
    }

    override fun setName(name: String?) {
        binding?.editTextName?.setText(name)
    }

    override fun onSave() {
        binding?.editTextId?.setText("")
        binding?.editTextName?.setText("")
        toast("保存成功")
    }
}