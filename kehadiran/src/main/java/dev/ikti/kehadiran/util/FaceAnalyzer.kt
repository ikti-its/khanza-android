package dev.ikti.kehadiran.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.YuvImage
import android.media.Image
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import java.io.ByteArrayOutputStream
import kotlin.math.sqrt

class FaceAnalyzer(
    interpreter: Interpreter,
    private val onDetectCallback: (Boolean) -> Unit
) : ImageAnalysis.Analyzer {
    private val faceNetInterpreter = interpreter
    private var recognizedFace: FloatArray = FloatArray(0)
    private val realTimeOpts = FaceDetectorOptions.Builder()
        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE) // Bisa ganti ke PERFORMANCE_MODE_ACCURATE atau PERFORMANCE_MODE_FAST
        .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
        .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_NONE)
        .setMinFaceSize(0.2f)
        .enableTracking()
        .build()

    private val faceNetImageProcessor = ImageProcessor.Builder()
        .add(
            ResizeOp(
                FACE_NET_IMAGE_SIZE,
                FACE_NET_IMAGE_SIZE,
                ResizeOp.ResizeMethod.BILINEAR
            )
        )
        .add(NormalizeOp(0f, 255f))
        .build()

    private val detector = FaceDetection.getClient(realTimeOpts)

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        mediaImage?.let {
            val inputImage = InputImage.fromMediaImage(it, imageProxy.imageInfo.rotationDegrees)

            detector.process(inputImage)
                .addOnSuccessListener { faces ->
                    onSuccess(faces, imageProxy, toBitmap(it))
                }.addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }

    private fun onSuccess(
        faces: List<Face>,
        imageProxy: ImageProxy,
        bitmap: Bitmap
    ) {
        if (faces.isNotEmpty()) {
            faces.forEach { face ->
                val converted = bitmap.copy(Bitmap.Config.ARGB_8888, true)
                val faceBitmap =
                    cropToBox(converted, face.boundingBox, imageProxy.imageInfo.rotationDegrees)
                        ?: return
                val tensorImage = TensorImage.fromBitmap(faceBitmap)
                val faceOutputArray = Array(1) {
                    FloatArray(192)
                }
                val faceNetByteBuffer = faceNetImageProcessor.process(tensorImage).buffer
                faceNetInterpreter.run(faceNetByteBuffer, faceOutputArray)

                if (recognizedFace.isNotEmpty()) {
                    Log.d("FACE_ANALYZER", "isSimilar: ${isSimilar(faceOutputArray[0])}")
                    onDetectCallback.invoke(isSimilar(faceOutputArray[0]))
                }
            }
        }
    }

    private fun toBitmap(image: Image): Bitmap {
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
        yuvImage.compressToJpeg(Rect(0, 0, yuvImage.width, yuvImage.height), 75, out)
        val imageBytes = out.toByteArray()
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    private fun isSimilar(vector: FloatArray): Boolean {
        val knownVector: FloatArray = recognizedFace
        var distance = 0f
        for (i in vector.indices) {
            val diff = vector[i] - knownVector[i]
            distance += diff * diff
        }
        distance = sqrt(distance.toDouble()).toFloat()

        Log.d("FACE_ANALYZER", "Distance: $distance")
        return (distance < 0.75f)
    }

    fun getPegawaiFace(bitmap: Bitmap) {
        val inputImage = InputImage.fromBitmap(bitmap, 0)

        detector.process(inputImage)
            .addOnSuccessListener { faces ->
                if (faces.isNotEmpty()) {
                    val face = faces[0]
                    val converted = bitmap.copy(Bitmap.Config.ARGB_8888, true)
                    val faceBitmap =
                        cropToBox(converted, face.boundingBox, inputImage.rotationDegrees)
                            ?: return@addOnSuccessListener
                    val tensorImage = TensorImage.fromBitmap(faceBitmap)
                    val faceOutputArray = Array(1) {
                        FloatArray(192)
                    }
                    val faceNetByteBuffer = faceNetImageProcessor.process(tensorImage).buffer
                    faceNetInterpreter.run(faceNetByteBuffer, faceOutputArray)

                    recognizedFace = faceOutputArray[0]
                }
            }
    }

    private fun cropToBox(bitmap: Bitmap, bounding: Rect, rotation: Int): Bitmap? {
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

    companion object {
        private const val FACE_NET_IMAGE_SIZE = 112
    }
}