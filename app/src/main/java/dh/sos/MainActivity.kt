package dh.sos

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.idenfy.permissionissue.R

class MainActivity : AppCompatActivity() {
    var permissionsDelegateUtil = PermissionsDelegateUtil()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(PermissionsDelegateUtil.RESULT_TAG, "onCreate")
        if (!permissionsDelegateUtil.hasCameraPermission(this))
            permissionsDelegateUtil.requestCameraPermission(this)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val result = permissionsDelegateUtil.resultGranted(this, requestCode, permissions, grantResults)
        Log.d(PermissionsDelegateUtil.RESULT_TAG, "onRequestPermissionsResult:$result")
        handleCameraPermissionResult(permissionResult = result)
    }

    private fun handleCameraPermissionResult(permissionResult: PermissionResult) {
        when (permissionResult) {
            PermissionResult.PermissionGranted -> {

            }
            PermissionResult.PermissionNotGrantedRetryAuto -> {
                permissionsDelegateUtil.requestCameraPermission(this)

            }
            PermissionResult.PermissionNotGrantedCantRetry -> {
                permissionsDelegateUtil.requestCameraPermission(this)
            }
            PermissionResult.PermissionNotGrantedDontAsk -> {
            }
        }
    }
}