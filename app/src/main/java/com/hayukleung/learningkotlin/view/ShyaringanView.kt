package com.hayukleung.learningkotlin.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet

/**
 * LearningKotlin
 *
 * liangxiaxu@aobi.com
 * 2017-12-13 14:49
 */
class ShyaringanView : BaseView {

    private val GOGOK_COUNT = 3
    private val DELTA_ANGLE = 6

    /** 写轮眼背景  */
    private var mPaintBackground: Paint? = null
    /** 写轮眼勾玉  */
    private var mPaintGogok: Paint? = null

    private var mPaintBitmap: Paint? = null

    private var mRectFGogok: RectF? = null
    private var mPathGogok: Path? = null
    private var mAngle = -DELTA_ANGLE

    private var mCanvasBuffer: Canvas? = null
    private var mBitmapBufferBackground: Bitmap? = null
    private var mBitmapBufferGogok: Bitmap? = null
    private var mMatrix: Matrix? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attributeSet, defStyleAttr, defStyleRes)

    override fun init(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        mPathGogok = Path()
        mMatrix = Matrix()
    }

    override fun onDraw(canvas: Canvas?) {
        if (null == mBitmapBufferBackground) {
            bufferBackground()
        }

        if (null == mBitmapBufferGogok) {
            bufferGogok()
        }

        if (null == mPaintBitmap) {
            mPaintBitmap = Paint(Paint.ANTI_ALIAS_FLAG)
        }

        mMatrix?.reset()
        canvas?.drawBitmap(mBitmapBufferBackground, mMatrix, mPaintBitmap)

        mMatrix?.reset()
        // 旋转
        mMatrix?.postRotate(getAngle().toFloat(), width.toFloat() / 2, height.toFloat() / 2)
        canvas?.drawBitmap(mBitmapBufferGogok, mMatrix, mPaintBitmap)

        postInvalidateDelayed(15)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val wSize = MeasureSpec.getSize(widthMeasureSpec)
        val hSize = MeasureSpec.getSize(heightMeasureSpec)
        val size = if (wSize < hSize) wSize else hSize
        val sizeMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY)
        setMeasuredDimension(sizeMeasureSpec, sizeMeasureSpec)
    }

    /**
     * 预先绘制背景
     */
    private fun bufferBackground() {
        if (null == mBitmapBufferBackground) {
            mBitmapBufferBackground = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444)
            mCanvasBuffer = Canvas(mBitmapBufferBackground)
        }
        drawShyaringanBackground(mCanvasBuffer)
    }

    /**
     * 预先绘制勾玉
     */
    private fun bufferGogok() {
        if (null == mBitmapBufferGogok) {
            mBitmapBufferGogok = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444)
            mCanvasBuffer = Canvas(mBitmapBufferGogok)
        }
        drawShyaringanGogok(mCanvasBuffer)
    }

    private fun getAngle(): Int {
        mAngle += DELTA_ANGLE
        mAngle %= 360
        return mAngle
    }

    /**
     * 绘制写轮眼背景
     *
     * @param canvas
     */
    private fun drawShyaringanBackground(canvas: Canvas?) {

        val cx = width / 2
        val cy = height / 2
        val radius = width / 2 / 10 * 9
        val strokeWidthUnit = width / 60

        if (null == mPaintBackground) {
            mPaintBackground = Paint()
            mPaintBackground?.setAntiAlias(true)
        }
        // 绘制背景颜色
        run {
            mPaintBackground?.setStyle(Paint.Style.FILL)
            mPaintBackground?.setColor(Color.argb(255, 200, 59, 68))
            canvas?.drawCircle(cx.toFloat(), cy.toFloat(), radius.toFloat(), mPaintBackground)
        }
        // 绘制黑环
        run {
            mPaintBackground?.setStyle(Paint.Style.STROKE)
            mPaintBackground?.setColor(Color.argb(255, 0, 0, 0))
            mPaintBackground?.setStrokeWidth((strokeWidthUnit * 2).toFloat())
            canvas?.drawCircle(cx.toFloat(), cy.toFloat(), radius.toFloat(), mPaintBackground)
            mPaintBackground?.setStrokeWidth(strokeWidthUnit.toFloat())
            canvas?.drawCircle(cx.toFloat(), cy.toFloat(), (radius / 5 * 3).toFloat(), mPaintBackground)
        }
        // 绘制眼珠
        run {
            mPaintBackground?.setStyle(Paint.Style.FILL)
            mPaintBackground?.setColor(Color.argb(255, 0, 0, 0))
            canvas?.drawCircle(cx.toFloat(), cy.toFloat(), (radius / 5).toFloat(), mPaintBackground)
        }
    }

    /**
     * 绘制写轮眼勾玉
     *
     * @param canvas
     */
    private fun drawShyaringanGogok(canvas: Canvas?) {

        val cx = width / 2
        val cy = height / 2
        val radius = width / 2 / 10 * 9
        val strokeWidthUnit = width / 60

        var count = GOGOK_COUNT
        while (count-- > 0) {

            if (null == mPaintGogok) {
                mPaintGogok = Paint()
                mPaintGogok?.setAntiAlias(true)
                mPaintGogok?.setStyle(Paint.Style.FILL)
                mPaintGogok?.setColor(Color.argb(255, 0, 0, 0))
                mPaintGogok?.setStrokeWidth(strokeWidthUnit.toFloat())
            }
            val rGogokUnit = width.toFloat() / 1000f
            if (null == mRectFGogok) {
                mRectFGogok = RectF()
            }
            mPathGogok?.reset()
            mRectFGogok?.set(
                    // l
                    cx - rGogokUnit * 50,
                    // t
                    cy.toFloat() - (radius / 5 * 3).toFloat() - rGogokUnit * 50,
                    // r
                    cx + rGogokUnit * 50,
                    // b
                    cy - radius / 5 * 3 + rGogokUnit * 50)
            mPathGogok?.arcTo(mRectFGogok, 90f, 180f)
            mRectFGogok?.set(
                    // l
                    cx - rGogokUnit * 15,
                    // t
                    cy.toFloat() - (radius / 5 * 3).toFloat() - rGogokUnit * 50,
                    // r
                    cx + rGogokUnit * 15,
                    // b
                    cy.toFloat() - (radius / 5 * 3).toFloat() - rGogokUnit * 20)
            mPathGogok?.arcTo(mRectFGogok, 270f, -180f)
            mRectFGogok?.set(
                    // l
                    cx - rGogokUnit * 35,
                    // t
                    cy.toFloat() - (radius / 5 * 3).toFloat() - rGogokUnit * 20,
                    // r
                    cx + rGogokUnit * 35,
                    // b
                    cy - radius / 5 * 3 + rGogokUnit * 50)
            mPathGogok?.arcTo(mRectFGogok, 270f, 180f)
            mPathGogok?.close()

            canvas?.save()
            canvas?.rotate((360 / GOGOK_COUNT * count).toFloat(), cx.toFloat(), cy.toFloat())
            canvas?.rotate(30f, cx.toFloat(), (cy - radius / 5 * 3).toFloat())
            canvas?.drawPath(mPathGogok, mPaintGogok)
            canvas?.restore()
        }
    }
}