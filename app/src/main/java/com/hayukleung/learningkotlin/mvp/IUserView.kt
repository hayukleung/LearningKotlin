package com.hayukleung.learningkotlin.mvp

/**
 * LearningKotlin
 *
 * liangxiaxu@aobi.com
 * 2017-12-13 15:23
 */
interface IUserView {

    fun getId(): Int
    fun getName(): String

    fun setId(id: Int?)
    fun setName(name: String?)

    fun onSave()
}