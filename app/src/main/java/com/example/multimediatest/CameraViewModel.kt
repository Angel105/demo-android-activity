package com.example.multimediatest

import android.graphics.Bitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class CameraViewModel(private val state: SavedStateHandle) : ViewModel() {
    companion object {
        private const val CURRENT_BITMAP_KEY = "current_bitmap"
    }

    var currentBitmap: Bitmap?
        get() = state.get<Bitmap>(CURRENT_BITMAP_KEY)
        set(value) = state.set(CURRENT_BITMAP_KEY, value)
}