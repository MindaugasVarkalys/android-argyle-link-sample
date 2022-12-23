package com.varkalys.argyle.ui.component.screen

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varkalys.argyle.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
open class ScreenViewModel @Inject constructor(): ViewModel() {

    private val _snackbars = MutableSharedFlow<Int>(extraBufferCapacity = 2)
    val snackbars = _snackbars as SharedFlow<Int>

    fun showSnackbar(@StringRes id: Int) {
        _snackbars.tryEmit(id)
    }

    protected fun launchNetworkRequest(
        onError: () -> Unit = {},
        request: suspend () -> Unit,
    ) {
        viewModelScope.launch {
            try {
                request()
            } catch (ex: IOException) {
                showSnackbar(R.string.error_check_connection)
                onError()
            } catch (ex: HttpException) {
                showSnackbar(R.string.error_server)
                onError()
            }
        }
    }
}