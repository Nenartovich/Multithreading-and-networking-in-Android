package com.nenartovich.example06_recyclerview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class CardDecoration : RecyclerView.ItemDecoration() {

    private val yellowPaint: Paint = Paint()
    private val redPaint: Paint = Paint()

    init {
        yellowPaint.style = Paint.Style.FILL
        yellowPaint.isAntiAlias = true
        yellowPaint.color = Color.YELLOW

        redPaint.style = Paint.Style.FILL
        redPaint.isAntiAlias = true
        redPaint.color = Color.RED
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val pixelOffset = parent.context.resources.getDimensionPixelOffset(R.dimen.ll_margin)

        for (child in parent.children) {
            val top = child.top - pixelOffset / 2
            val bottom = child.bottom + pixelOffset / 2
            val childAdapterPosition = parent.getChildAdapterPosition(child)
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(),
                if (childAdapterPosition % 2 == 0) redPaint else yellowPaint)
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val pixelOffset = parent.context.resources.getDimensionPixelOffset(R.dimen.ll_margin)
        val right = parent.width / 2
        val left = right - pixelOffset * 2

        for (child in parent.children) {
            val top = child.top + pixelOffset * 2
            val bottom = child.bottom - pixelOffset
            val position = parent.getChildAdapterPosition(child)
            c.drawRect(
                left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(),
                if (position % 2 == 0) yellowPaint else redPaint)
        }

    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val pixelOffset = view.context.resources.getDimensionPixelOffset(R.dimen.ll_margin)
        outRect.top = pixelOffset / 2
        outRect.bottom = pixelOffset / 2
        outRect.left = pixelOffset
        outRect.right = pixelOffset
    }



}