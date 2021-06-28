package www.painacafe.com.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import www.painacafe.com.R

class loginfragment : Fragment() {

    private lateinit var loginViewModel: loginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel =
            ViewModelProvider(this)
                .get(www.painacafe.com.ui.login.loginViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        loginViewModel.text.observe(viewLifecycleOwner, Observer {
        })


        return root
    }
}