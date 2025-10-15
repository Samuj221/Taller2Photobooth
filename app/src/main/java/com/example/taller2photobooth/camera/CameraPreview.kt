package com.example.taller2photobooth.camera

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun rememberCameraController(): LifecycleCameraController {
    val context = LocalContext.current
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_CAPTURE)
            cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        }
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        controller.bindToLifecycle(lifecycleOwner)
        onDispose { }
    }
    return controller
}

@Composable
fun CameraPreview(
    controller: LifecycleCameraController,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            PreviewView(ctx).apply {
                this.controller = controller
                this.scaleType = PreviewView.ScaleType.FILL_CENTER
                this.implementationMode = PreviewView.ImplementationMode.COMPATIBLE
            }
        }
    )
}

fun toggleCamera(controller: LifecycleCameraController) {
    controller.cameraSelector =
        if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA)
            CameraSelector.DEFAULT_FRONT_CAMERA
        else
            CameraSelector.DEFAULT_BACK_CAMERA
}

fun takePhoto(
    context: Context,
    controller: LifecycleCameraController,
    onSuccess: (Uri) -> Unit,
    onError: (Throwable) -> Unit = {}
) {
    val file = createImageFile(context)
    val output = ImageCapture.OutputFileOptions.Builder(file).build()

    controller.takePicture(
        output,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(result: ImageCapture.OutputFileResults) {
                val uri = result.savedUri ?: Uri.fromFile(file)
                Toast.makeText(context, "Foto guardada ✅", Toast.LENGTH_SHORT).show()
                onSuccess(uri)
            }
            override fun onError(exception: ImageCaptureException) {
                Toast.makeText(context, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
                onError(exception)
            }
        }
    )
}

private fun createImageFile(context: Context): File {
    val dir = context.getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES)
        ?: context.filesDir
    val name = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    return File(dir, "PB_$name.jpg")
}
