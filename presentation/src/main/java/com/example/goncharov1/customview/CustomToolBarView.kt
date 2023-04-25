package com.example.goncharov1.customview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import com.example.goncharov1.R

class CustomToolBarView : Toolbar {

    private var sashaFingerBackArrow: Boolean = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.customToolBar,
            0, 0
        ).apply {
            sashaFingerBackArrow = getBoolean(R.styleable.customToolBar_sashaFingerBackArrow, false)
        }
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (sashaFingerBackArrow) {
            setNavigationIcon(R.drawable.back_arrow)
        }
    }
}