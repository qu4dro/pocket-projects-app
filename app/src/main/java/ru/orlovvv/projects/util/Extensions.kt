package ru.orlovvv.projects.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.orlovvv.projects.util.Constants.SPACING

fun RecyclerView.setStickersSpacing() {
    this.addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.set(SPACING, SPACING, SPACING, SPACING);
        }
    })
}