package com.hayukleung.learningkotlin.mvp

/**
 * LearningKotlin
 *
 * liangxiaxu@aobi.com
 * 2017-12-13 15:50
 */
class UserPresenter(val userView: IUserView): IUserPresenter {

    private val userModel = UserModel()

    override fun save(id: Int, name: String) {
        userModel.setId(id)
        userModel.setName(name)
        userModel.save()
        userView.onSave()
    }

    override fun load(id: Int) {
        val user = userModel.load(id)
        userView.setId(user?.id)
        userView.setName(user?.name)
    }
}