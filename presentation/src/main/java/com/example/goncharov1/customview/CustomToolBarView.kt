package com.example.goncharov1.customview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import com.example.goncharov1.R

class CustomToolBarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Toolbar(context, attributeSet, defStyleAttr) {

    private var sashaFingerBackArrow: Boolean = false

    init {
        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.customToolBar,
            0, 0
        ).apply {
            sashaFingerBackArrow = getBoolean(R.styleable.customToolBar_sashaFingerBackArrow, false)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (sashaFingerBackArrow) {
            setNavigationIcon(R.drawable.back_arrow)
        }
    }
}