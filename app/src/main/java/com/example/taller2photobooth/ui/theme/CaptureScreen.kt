package com.example.taller2photobooth.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.taller2photobooth.PhotoboothViewModel
import com.example.taller2photobooth.R
import com.example.taller2photobooth.camera.CameraPreview
import com.example.taller2photobooth.camera.rememberCameraController
import com.example.taller2photobooth.camera.takePhoto
import com.example.taller2photobooth.camera.toggleCamera

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CaptureScreen(vm: PhotoboothViewModel) {
    val context = LocalContext.current
    val controller = rememberCameraController()

    val screenHeight = LocalConfiguration.current.screenHeightDp
    val previewHeight = (screenHeight * 0.30f).dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        // Vista de cámara: ancho completo y 30% alto
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(previewHeight)
                .clip(MaterialTheme.shapes.large)
        ) {
            CameraPreview(controller = controller, modifier = Modifier.fillMaxSize())

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FilledTonalIconButton(onClick = { toggleCamera(controller) }, modifier = Modifier.size(48.dp)) {
                    Icon(Icons.Default.Cameraswitch, contentDescription = "Cambiar")
                }
                Spacer(Modifier.weight(1f))
                FilledIconButton(
                    onClick = { takePhoto(context, controller) { uri -> vm.addPhoto(uri) } },
                    modifier = Modifier.size(56.dp)
                ) {
                    Icon(Icons.Default.PhotoCamera, contentDescription = "Tomar foto")
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Galería de la sesión (vacía al iniciar)
        if (vm.sessionPhotos.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(Icons.Default.PhotoCamera, contentDescription = null, modifier = Modifier.size(48.dp))
                Spacer(Modifier.height(8.dp))
                Text(text = stringResource(R.string.no_photos))
            }
        } else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(120.dp),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(vm.sessionPhotos) { uri ->
                    AsyncImage(
                        model = uri,
                        contentDescription = "Foto",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 120.dp)
                            .clip(MaterialTheme.shapes.medium)
                    )
                }
            }
        }
    }
}
