package com.hoony.kotlinsample.behavior.floating_button_example

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlin.math.min

class FloatingButtonTranslationBehavior(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<FloatingActionButton>(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: FloatingActionButton,
        dependency: View
    ): Boolean {
        return dependency is Snackbar.SnackbarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: FloatingActionButton,
        dependency: View
    ): Boolean {
        val translationY = min(0f, (dependency.translationY - dependency.height))
        Log.d(
            "translation",
            "\n dependency.translationY : ${dependency.translationY} \n" +
                    "dependency.height : ${dependency.height} \n" +
                    "translationY : $translationY"
        )
        child.translationY = translationY

        return false
    }
}