package com.taylorcase.hearthstonescry.base

import android.support.annotation.LayoutRes

/**
 * Helps inflate activities in the base view so we can do
 * all nav drawer inflating there.
 */
@Target(AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class InjectLayout(@LayoutRes val value: Int)

