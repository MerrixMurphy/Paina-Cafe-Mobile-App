package www.painacafe.com.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import www.painacafe.com.R

class AccountFragment : Fragment() {

    private lateinit var AccountViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AccountViewModel =
            ViewModelProvider(this)
                .get(www.painacafe.com.ui.account.AccountViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_account, container, false)
        AccountViewModel.text.observe(viewLifecycleOwner, Observer {
        })

        val wardbox: LinearLayout? = view?.findViewById(R.id.wardbox)
        val wardpass: LinearLayout? = view?.findViewById(R.id.wardpass)
        if (wardbox != null) {
            wardbox.visibility = View.GONE
        }
        if (wardpass != null) {
            wardpass.visibility = View.GONE
        }

        return root
    }
}
