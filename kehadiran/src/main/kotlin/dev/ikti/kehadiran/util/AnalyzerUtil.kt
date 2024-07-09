package dev.ikti.kehadiran.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.YuvImage
import android.media.Image
import java.io.ByteArrayOutputStream

fun toBitmap(image: Image): Bitmap {
    val planes = image.planes
    val yBuffer = planes[0].buffer
    val uBuffer = planes[1].buffer
    val vBuffer = planes[2].buffer
    val ySize = yBuffer.remaining()
    val uSize = uBuffer.remaining()
    val vSize = vBuffer.remaining()
    val nv21 = ByteArray(ySize + uSize + vSize)
    yBuffer[nv21, 0, ySize]
    vBuffer[nv21, ySize, vSize]
    uBuffer[nv21, ySize + vSize, uSize]
    val yuvImage = YuvImage(nv21, ImageFormat.NV21, image.width, image.height, null)
    val out = ByteArrayOutputStream()
    yuvImage.compressToJpeg(Rect(0, 0, yuvImage.width, yuvImage.height), 90, out)
    val imageBytes = out.toByteArray()
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}

fun cropToBox(bitmap: Bitmap, bounding: Rect, rotation: Int): Bitmap? {
    val converted = bitmap.copy(Bitmap.Config.ARGB_8888, true)
    var image = converted
    if (rotation != 0) {
        val matrix = Matrix()
        matrix.postRotate(rotation.toFloat())
        image = Bitmap.createBitmap(image, 0, 0, image.width, image.height, matrix, true)
    }
    return if (bounding.top >= 0 && bounding.bottom <= image.width && bounding.top + bounding.height() <= image.height && bounding.left >= 0 && bounding.left + bounding.width() <= image.width) {
        Bitmap.createBitmap(
            image,
            bounding.left,
            bounding.top,
            bounding.width(),
            bounding.height()
        )
    } else null
}