package com.sigeschool.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bytedeco.javacv.CanvasFrame
import org.bytedeco.javacv.Frame
import org.bytedeco.javacv.OpenCVFrameConverter
import org.bytedeco.javacv.OpenCVFrameGrabber
import org.bytedeco.opencv.global.opencv_imgcodecs
import java.io.ByteArrayOutputStream
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

@Composable
actual fun CameraCaptureComponent(
    onPhotoCaptured: (ByteArray) -> Unit,
    modifier: Modifier
) {
    var isCapturing by remember { mutableStateOf(false) }
    var previewImage by remember { mutableStateOf<BufferedImage?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
            val grabber = OpenCVFrameGrabber(0) // Cámara predeterminada
            try {
                grabber.start()
                while (true) {
                    val frame = grabber.grab() ?: break
                    val bufferedImage = Java2DFrameConverter.toBufferedImage(frame)
                    withContext(Dispatchers.Main) {
                        previewImage = bufferedImage
                    }
                    delay(33) // ~30 FPS
                }
            } catch (e: Exception) {
                error = "No se pudo acceder a la cámara: ${e.message}"
            } finally {
                grabber.stop()
                grabber.release()
            }
        }
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (error != null) {
            Text(error!!, color = androidx.compose.ui.graphics.Color.Red)
        } else if (previewImage != null) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    bitmap = previewImage!!.toComposeImageBitmap(),
                    contentDescription = "Preview",
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    scope.launch(Dispatchers.IO) {
                        val baos = ByteArrayOutputStream()
                        ImageIO.write(previewImage, "jpg", baos)
                        onPhotoCaptured(baos.toByteArray())
                    }
                }) {
                    Text("Capturar Foto")
                }
            }
        } else {
            CircularProgressIndicator()
        }
    }
}

/**
 * Helper class for JavaCV to BufferedImage conversion
 * Note: In a real project, this might be a separate utility.
 */
object Java2DFrameConverter {
    fun toBufferedImage(frame: Frame): BufferedImage {
        val converter = org.bytedeco.javacv.Java2DFrameConverter()
        return converter.convert(frame)
    }
}
