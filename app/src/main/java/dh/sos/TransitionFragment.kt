package dh.sos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.idenfy.permissionissue.R



class TransitionFragment : Fragment(R.layout.fragment_transition) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!PermissionsDelegateUtil().hasCameraPermission(requireContext())) {
            findNavController().navigate(R.id.fragmentPermission)
        }
    }
}