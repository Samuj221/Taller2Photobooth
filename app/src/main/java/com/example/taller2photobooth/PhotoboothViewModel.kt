package com.example.taller2photobooth

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

/**
 * Galería de la SESIÓN (se limpia al matar la app).
 * Las fotos se guardan en almacenamiento externo de la app,
 * pero NO se recargan al iniciar (según enunciado).
 */
class PhotoboothViewModel : ViewModel() {
    private val _sessionPhotos = mutableStateListOf<Uri>()
    val sessionPhotos: List<Uri> get() = _sessionPhotos

    fun addPhoto(uri: Uri) { _sessionPhotos.add(0, uri) }
    fun clearSession() { _sessionPhotos.clear() }
}
