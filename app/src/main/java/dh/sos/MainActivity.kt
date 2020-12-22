package dh.sos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.idenfy.permissionissue.R

class MainActivity : AppCompatActivity() {
    var permissionsDelegateUtil = PermissionsDelegateUtil()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(PermissionsDelegateUtil.RESULT_TAG, "onCreate")
        if (savedInstanceState == null) {
            if (!permissionsDelegateUtil.hasCameraPermission(this))
                permissionsDelegateUtil.requestCameraPermission(this)
        } else {
            recreateMainActivity()
        }
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

    private fun recreateMainActivity() {
        val intent = Intent(this, MainActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NO_ANIMATION or
                Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)

        startActivity(intent)
    }
}