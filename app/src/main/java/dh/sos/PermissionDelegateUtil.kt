package dh.sos

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.Keep
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class PermissionsDelegateUtil {
    fun hasCameraPermission(fragment: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            fragment,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestCameraPermission(fragment: Fragment) {
        fragment.requestPermissions(
            arrayOf(Manifest.permission.CAMERA),
            PermissionsDelegateUtil.REQUEST_PERMISSIONS_CAMERA
        )
    }

    fun resultGranted(
        fragment: Fragment, requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ): PermissionResult {
        if (requestCode != REQUEST_PERMISSIONS_CAMERA) {
            Log.d(RESULT_TAG, "requestCode does not match")
            return PermissionResult.PermissionNotGrantedDontAsk
        }
        if (grantResults.isEmpty()) {
            Log.d(RESULT_TAG, "grantResults.isEmpty()")
            return PermissionResult.PermissionNotGrantedDontAsk
        }
        if (permissions[0] != Manifest.permission.CAMERA) {
            Log.d(RESULT_TAG, "not camera")
            return PermissionResult.PermissionNotGrantedDontAsk
        }
        return if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            PermissionResult.PermissionGranted

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (fragment.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    PermissionResult.PermissionNotGrantedRetryAuto
                } else {
                    ///User selected don't show again checkbox.
                    PermissionResult.PermissionNotGrantedCantRetry
                }
            } else {
                PermissionResult.PermissionNotGrantedRetryAuto
            }
        }
    }

    @Keep
    companion object {
        const val RESULT_TAG = "CAMERA_PERMISSION"
        const val REQUEST_PERMISSIONS_CAMERA = 1001
    }

}

sealed class PermissionResult {
    object PermissionGranted : PermissionResult()
    object PermissionNotGrantedRetryAuto : PermissionResult()
    object PermissionNotGrantedDontAsk : PermissionResult()
    object PermissionNotGrantedCantRetry : PermissionResult()
}