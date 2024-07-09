package dev.ikti.kehadiran.util

import android.graphics.Bitmap
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import dev.ikti.kehadiran.util.AnalyzerConstant.FACE_NET_IMAGE_SIZE
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import kotlin.math.sqrt

class FaceAnalyzer(
    interpreter: Interpreter,
    private val onDetectCallback: (Boolean) -> Unit
) : ImageAnalysis.Analyzer {
    private val faceNetInterpreter = interpreter
    private var recognizedFace: FloatArray = FloatArray(0)
    private val realTimeOpts = FaceDetectorOptions.Builder()
        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
        .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
        .setContourMode(FaceDetectorOptions.CONTOUR_MODE_NONE)
        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_NONE)
        .setMinFaceSize(0.75f)
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
                val faceOutputArray = Array(1) { FloatArray(192) }
                val faceNetByteBuffer = faceNetImageProcessor.process(tensorImage).buffer
                faceNetInterpreter.run(faceNetByteBuffer, faceOutputArray)

                if (recognizedFace.isNotEmpty()) {
                    onDetectCallback.invoke(isSimilar(faceOutputArray[0]))
                }
            }
        }
    }

    private fun isSimilar(vector: FloatArray): Boolean {
        val knownVector: FloatArray = recognizedFace
        var distance = 0f
        for (i in vector.indices) {
            val diff = vector[i] - knownVector[i]
            distance += diff * diff
        }
        distance = sqrt(distance.toDouble()).toFloat()

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
}