package com.hayukleung.learningkotlin.mvp

/**
 * LearningKotlin
 *
 * liangxiaxu@aobi.com
 * 2017-12-13 15:49
 */
interface IUserPresenter {

    fun save(id: Int, name: String)
    fun load(id: Int)
}