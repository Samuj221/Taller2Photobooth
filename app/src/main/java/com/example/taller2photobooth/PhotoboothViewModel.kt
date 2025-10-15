package com.example.taller2photobooth

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class PhotoboothViewModel : ViewModel() {
    private val _sessionPhotos = mutableStateListOf<Uri>()
    val sessionPhotos: List<Uri> get() = _sessionPhotos
    fun addPhoto(uri: Uri) { _sessionPhotos.add(0, uri) }
    fun clearSession() { _sessionPhotos.clear() }
}
