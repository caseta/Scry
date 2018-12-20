package com.taylorcase.hearthstonescry.utils

import android.view.View
import android.view.View.*

fun View.makeVisible() {
    visibility = VISIBLE
}

fun View.makeGone() {
    visibility = GONE
}

fun View.makeInvisible() {
    visibility = INVISIBLE
}

fun View.isVisible(): Boolean {
    return visibility == VISIBLE
}

fun View.isGone(): Boolean {
    return visibility == GONE
}

fun View.isInvisible(): Boolean {
    return visibility == INVISIBLE
}
