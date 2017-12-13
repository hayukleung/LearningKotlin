package com.hayukleung.learningkotlin.mvp

import com.hayukleung.learningkotlin.javabean.User

/**
 * LearningKotlin
 *
 * liangxiaxu@aobi.com
 * 2017-12-13 15:40
 */
interface IUserModel {

    fun getId(): Int
    fun getName(): String

    fun setId(id: Int)
    fun setName(name: String)

    fun save()
    fun load(id: Int): User?
}