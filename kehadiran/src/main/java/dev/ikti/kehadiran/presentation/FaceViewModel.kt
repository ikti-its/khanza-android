package dev.ikti.kehadiran.presentation

import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ikti.kehadiran.domain.usecase.PresensiGetFotoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FaceViewModel @Inject constructor(
    private val application: Application,
    private val getFotoUseCase: PresensiGetFotoUseCase
) : ViewModel() {
    private val _dataBitmap = MutableStateFlow<Bitmap?>(null)
    val dataBitmap: StateFlow<Bitmap?> = _dataBitmap

    fun getFoto(token: String, pegawai: String) {
        viewModelScope.launch {
            val response = getFotoUseCase.execute(token, pegawai)
            response.collect { res ->
                val loader = ImageLoader(application)
                val request = ImageRequest.Builder(application)
                    .data(res.data.foto)
                    .build()

                val result = (loader.execute(request) as SuccessResult).drawable
                val bitmap = (result as BitmapDrawable).bitmap

                _dataBitmap.value = bitmap
            }
        }
    }
}
