package com.hayukleung.learningkotlin.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.View.MeasureSpec.getMode
import android.view.View.MeasureSpec.getSize

/**
 * LearningKotlin
 *
 * liangxiaxu@aobi.com
 * 2017-12-13 14:32
 */
abstract class BaseView : View {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        init(context, attributeSet, defStyleAttr, 0)
    }
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attributeSet, defStyleAttr, defStyleRes) {
        init(context, attributeSet, defStyleAttr, defStyleRes)
    }

    abstract fun init(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)

    /**
     * Compare to: [android.view.View.getDefaultSize]
     * If mode is AT_MOST, return the child size instead of the parent size
     * (unless it is too big).
     *
     * int result = size;
     * int specMode = MeasureSpec.getMode(measureSpec);
     * int specSize = MeasureSpec.getSize(measureSpec);
     * switch (specMode) {
     * case MeasureSpec.UNSPECIFIED:
     * result = size;
     * break;
     * case MeasureSpec.AT_MOST:
     * case MeasureSpec.EXACTLY:
     * result = specSize;
     * break;
     * }
     * return result;
     */
    protected fun getDefaultSize2(size: Int, measureSpec: Int): Int {
        var result = size
        val specMode = getMode(measureSpec)
        val specSize = getSize(measureSpec)

        when (specMode) {
            View.MeasureSpec.UNSPECIFIED -> result = size
            View.MeasureSpec.AT_MOST -> result = Math.min(size, specSize)
            View.MeasureSpec.EXACTLY -> result = specSize
        }
        return result
    }
}