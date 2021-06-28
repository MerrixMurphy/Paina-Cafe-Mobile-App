package www.painacafe.com.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import www.painacafe.com.R

class registerfragment : Fragment() {

    private lateinit var registerViewModel: registerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registerViewModel =
            ViewModelProvider(this)
                .get(www.painacafe.com.ui.register.registerViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        registerViewModel.text.observe(viewLifecycleOwner, Observer {
        })


        return root
    }
}