package com.taylorcase.hearthstonescry.filter

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Checkable
import android.widget.FrameLayout

class CheckableFrameLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0,
        defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyle, defStyleRes), Checkable {

    private var checked: Boolean = false

    override fun isChecked(): Boolean {
        return checked
    }

    override fun toggle() {
        return setChecked(!checked)
    }

    override fun setChecked(checked: Boolean) {
        if (checked != this.checked) {
            this.checked = checked
            refreshDrawableState()
        }
    }

    private val CheckedStateSet = intArrayOf(android.R.attr.state_checked)

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (isChecked) {
            View.mergeDrawableStates(drawableState, CheckedStateSet)
        }
        return drawableState
    }

    override fun performClick(): Boolean {
        toggle()
        return super.performClick()
    }
}
