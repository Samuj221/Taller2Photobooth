package com.example.taller2photobooth

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taller2photobooth.ui.CaptureScreen
import com.example.taller2photobooth.ui.PermissionScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            com.example.taller2photobooth.ui.theme.Taller2PhotoboothTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val vm: PhotoboothViewModel = viewModel()
                    val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA)

                    if (cameraPermission.status.isGranted) {
                        CaptureScreen(vm = vm)
                    } else {
                        PermissionScreen(onRequest = { cameraPermission.launchPermissionRequest() })
                    }
                }
            }
        }

    }
}
