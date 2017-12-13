package com.hayukleung.learningkotlin.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.hayukleung.learningkotlin.R
import com.hayukleung.learningkotlin.databinding.ActivityDemo004Binding
import com.hayukleung.learningkotlin.functionextension.format
import com.hayukleung.learningkotlin.javabean.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

/**
 * LearningKotlin
 *
 * liangxiaxu@aobi.com
 * 2017-12-12 16:27
 */
class Demo004Activity : AppCompatActivity() {

    private var binding: ActivityDemo004Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_demo_004)
        binding?.let {
            it.button.setOnClickListener {
                Log.e("time-start", format(System.currentTimeMillis()))
                concat()
            }
        }
    }

    private fun getObservable(id: Int): Observable<User> {
        return Observable.create {
            // Thread.sleep(Random().nextInt(1000).toLong())
            Thread.sleep(1000)
            val user = User(id, String.format(Locale.CHINA, "%d", id))
            it.onNext(user)
            it.onComplete()
        }
    }

    private fun merge() {
        // merge 异步操作，不保证处理结果按请求顺序返回
        Observable.merge(
                getObservable(0).subscribeOn(Schedulers.newThread()),
                getObservable(1).subscribeOn(Schedulers.newThread()),
                getObservable(2).subscribeOn(Schedulers.newThread()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe(
                        // onSuccess
                        { userList ->
                            Log.e("onSuccess - 0", userList[0].name)
                            Log.e("onSuccess - 1", userList[1].name)
                            Log.e("onSuccess - 2", userList[2].name)
                            Log.e("time-end", format(System.currentTimeMillis()))
                        },
                        // onError
                        { throwable ->
                            Log.e("onError", throwable.message)
                        }
                )
    }

    private fun concat() {
        // concat 同步操作，处理结果按请求顺序返回
        Observable.concat(
                getObservable(0).subscribeOn(Schedulers.newThread()),
                getObservable(1).subscribeOn(Schedulers.newThread()),
                getObservable(2).subscribeOn(Schedulers.newThread()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe(
                        // onSuccess
                        { userList ->
                            Log.e("onSuccess - 0", userList[0].name)
                            Log.e("onSuccess - 1", userList[1].name)
                            Log.e("onSuccess - 2", userList[2].name)
                            Log.e("time-end", format(System.currentTimeMillis()))
                        },
                        // onError
                        { throwable ->
                            Log.e("onError", throwable.message)
                        }
                )
    }

    private fun zip() {
        // zip 异步操作，处理结果按请求顺序返回
        Observable.zip(
                getObservable(0).subscribeOn(Schedulers.newThread()),
                getObservable(1).subscribeOn(Schedulers.newThread()),
                getObservable(2).subscribeOn(Schedulers.newThread()),
                Function3<User, User, User, List<User>> { user0, user1, user2 ->
                    val userList: MutableList<User> = ArrayList()
                    userList.add(user0)
                    userList.add(user1)
                    userList.add(user2)
                    return@Function3 userList
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        // onNext
                        { userList ->
                            Log.e("onSuccess - 0", userList[0].name)
                            Log.e("onSuccess - 1", userList[1].name)
                            Log.e("onSuccess - 2", userList[2].name)
                            Log.e("time-end", format(System.currentTimeMillis()))
                        },
                        // onError
                        { throwable ->
                            Log.e("onError", throwable.message)
                        }
                )
    }
}