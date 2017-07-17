package com.ljb.mvp.kotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout


/**
 * 底部Tab导航栏
 * Created by L on 2017/7/10.
 */
class TabGroupView : LinearLayout {

    var mOnItemClickListener: ((position: Int) -> Unit)? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setOnItemClickListener(listener: (position: Int) -> Unit) {
        mOnItemClickListener = listener
    }

    fun setAdapter(adapter: TabAdapter?) {
        if (adapter != null && adapter.count > 0) {
            for (i in 0..adapter.count - 1) {
                val tabView = adapter.getTabView(i)
                val params = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
                params.weight = 1f
                params.gravity = Gravity.CENTER
                addView(tabView, params)
                tabView.setOnClickListener { mOnItemClickListener?.invoke(i) }
            }
        }
    }

    fun setSelectedPosition(position: Int) {
        initUnSelected()
        getChildAt(position).isSelected = true
    }

    private fun initUnSelected() {
        for (i in 0..childCount - 1) {
            getChildAt(i).isSelected = false
        }
    }


    interface TabAdapter {
        val count: Int
        fun getTabView(position: Int): View
    }


}
