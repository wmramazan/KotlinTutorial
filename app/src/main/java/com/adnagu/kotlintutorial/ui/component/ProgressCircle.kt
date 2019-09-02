package com.adnagu.kotlintutorial.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.ViewCompat
import com.adnagu.kotlintutorial.R

/**
 * Created on 8/28/2019
 * @author wmramazan
 */
class ProgressCircle : RelativeLayout {

    private lateinit var progress: ProgressBar

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        setBackgroundResource(R.drawable.background_progress)
        View.inflate(context, R.layout.component_progress_circle, this)

        progress = findViewById(R.id.component_progress)
        DrawableCompat.setTint(
            progress.indeterminateDrawable,
            ContextCompat.getColor(context, R.color.colorPrimaryDark)
        )

        ViewCompat.setElevation(this, 3f)
    }
}
