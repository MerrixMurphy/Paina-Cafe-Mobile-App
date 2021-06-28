package www.painacafe.com.uivegas.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            ViewModelProvider(this).get(www.painacafe.com.uivegas.account.AccountViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_account_vegas, container, false)
        AccountViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return root
    }
}
