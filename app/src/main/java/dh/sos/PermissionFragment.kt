package dh.sos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.idenfy.permissionissue.R


class PermissionFragment : Fragment(R.layout.fragment_permission) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            PermissionsDelegateUtil().requestCameraPermission(this)
        } else {
            recreateMainActivity()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val result =
            PermissionsDelegateUtil().resultGranted(this, requestCode, permissions, grantResults)
        Log.d(PermissionsDelegateUtil.RESULT_TAG, "onRequestPermissionsResult:$result")
        handleCameraPermissionResult(permissionResult = result)
    }

    private fun handleCameraPermissionResult(permissionResult: PermissionResult) {
        when (permissionResult) {
            PermissionResult.PermissionGranted -> {
                findNavController().popBackStack()
            }
            PermissionResult.PermissionNotGrantedRetryAuto -> {
                PermissionsDelegateUtil().requestCameraPermission(this)
            }
            PermissionResult.PermissionNotGrantedCantRetry -> {
                PermissionsDelegateUtil().requestCameraPermission(this)
            }
            PermissionResult.PermissionNotGrantedDontAsk -> {
            }
        }
    }


    private fun recreateMainActivity() {
        val intent = Intent(activity, MainActivity::class.java)

        intent.addFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NO_ANIMATION or
                    Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
        )

        startActivity(intent)
    }
}