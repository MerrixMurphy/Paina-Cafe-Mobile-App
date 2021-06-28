package www.painacafe.com.uikoko.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_notifications.*
import www.painacafe.com.R

class NotificationsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var googleMap: GoogleMap

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        map_view.onCreate(savedInstanceState)
        map_view.onResume()
        map_view.getMapAsync(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications_koko, container, false)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return root
    }
    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            googleMap = it
            val Ward = LatLng(21.278145, -157.705457)
            googleMap.setMinZoomPreference(10.0f)
            googleMap.setMaxZoomPreference(21.0f)
            googleMap.addMarker(MarkerOptions().position(Ward).title("Marker in Ward Center"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(Ward))
        }
    }
}
