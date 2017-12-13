package com.hayukleung.learningkotlin.mvp

import android.util.SparseArray
import com.hayukleung.learningkotlin.javabean.User

/**
 * LearningKotlin
 *
 * liangxiaxu@aobi.com
 * 2017-12-13 15:42
 */
class UserModel : IUserModel {

    private var id: Int = 0
    private var name: String = ""
    private val database = SparseArray<User>()

    override fun getId(): Int {
        return this.id
    }

    override fun getName(): String {
        return this.name
    }

    override fun setId(id: Int) {
        this.id = id
    }

    override fun setName(name: String) {
        this.name = name
    }

    override fun save() {
        database.put(this.id, User(this.id, this.name))
    }

    override fun load(id: Int): User? {
        this.id = id
        return database.get(this.id, null)
    }
}