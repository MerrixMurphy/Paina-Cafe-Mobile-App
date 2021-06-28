package www.painacafe.com

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rengwuxian.materialedittext.MaterialEditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import www.painacafe.com.Retrofit.IMyService
import www.painacafe.com.Retrofit.RetrofitClient
import www.painacafe.com.ui.login.loginfragment
import www.painacafe.com.ui.register.registerfragment

class MainActivity : AppCompatActivity() {

    private var compositeDisposable = CompositeDisposable()
    private lateinit var iMyService: IMyService

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }

    private fun loginUser(email: String, password: String) {
        val retrofitClient: Retrofit = RetrofitClient.getInstance()
        iMyService = retrofitClient.create(IMyService::class.java)
        val wb: TextView = findViewById(R.id.wardbox)
        val wp: TextView = findViewById(R.id.wardpass)
        wb.visibility = View.GONE
        wp.visibility = View.GONE
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }
        compositeDisposable.add(
            iMyService.loginUser(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    Toast.makeText(
                        this@MainActivity,
                        "" + response,
                        Toast.LENGTH_SHORT
                    ).show()
                    if (response == "Login Success") {
                        setContentView(R.layout.fragment_account)
                        val fragmentTransaction: FragmentTransaction =
                            supportFragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.wardaccount, loginfragment())
                        fragmentTransaction.commit()
                    }
                }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        overridePendingTransition(
            R.anim.nav_default_pop_enter_anim,
            R.anim.nav_default_pop_exit_anim
        )
        supportActionBar?.hide()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_account
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)




        val time = 0
        val open = Runnable {
            val retrofitClient: Retrofit = RetrofitClient.getInstance()
            iMyService = retrofitClient.create(IMyService::class.java)
                val openDialog = Dialog(this@MainActivity)
                openDialog.setContentView(R.layout.fragment_register)
                val wb: TextView = openDialog.findViewById(R.id.wardbox)
                val wp: TextView = openDialog.findViewById(R.id.wardpass)
                val preferences = getSharedPreferences("wardlog", Context.MODE_PRIVATE)
                val preferenceslog = getSharedPreferences("wardpas", Context.MODE_PRIVATE)
                val preflog = preferences.getString("wardlogin", "na")
                val prefpas = preferenceslog.getString("wardpass", "na")
                wb.text = preflog
                wp.text = prefpas
                val editor = preferences.edit()
                val editorlog = preferenceslog.edit()
                editor.putString("wardlogin", wb.text.toString())
                editorlog.putString("wardpass", wp.text.toString())
                editor.apply()
                editorlog.apply()
                if (preflog != "na" && prefpas != "na") {
                    Handler().postDelayed({
                        loginUser(
                            wb.text.toString(),
                            wp.text.toString()
                        )
                    }, time.toLong())
                }
            }
        open.run()
    }

        fun btn_login_ward(view: View) {
            val check: CheckBox = findViewById(R.id.checkbox_ward)
            val retrofitClient: Retrofit = RetrofitClient.getInstance()
            iMyService = retrofitClient.create(IMyService::class.java)
                val edt_login_email: MaterialEditText = findViewById(R.id.wardemailinput)
                val edt_login_password: MaterialEditText = findViewById(R.id.wardpasswordinput)
                loginUser(
                    edt_login_email.text.toString(),
                    edt_login_password.text.toString()
                )
                if (check.isChecked) {
                    val wb: TextView = findViewById(R.id.wardbox)
                    val wp: TextView = findViewById(R.id.wardpass)
                    val preferences = getSharedPreferences("wardlog", Context.MODE_PRIVATE)
                    val preferenceslog = getSharedPreferences("wardpas", Context.MODE_PRIVATE)
                    wb.text = edt_login_email.toString()
                    wp.text = edt_login_password.toString()
                    val editor = preferences.edit()
                    val editorlog = preferenceslog.edit()
                    editor.putString("wardlogin", wb.text.toString())
                    editorlog.putString("wardpass", wp.text.toString())
                    editor.apply()
                    editorlog.apply()
                } else {
                    val wb: TextView = findViewById(R.id.wardbox)
                    val wp: TextView = findViewById(R.id.wardpass)
                    val preferences = getSharedPreferences("wardlog", Context.MODE_PRIVATE)
                    val preferenceslog = getSharedPreferences("wardpas", Context.MODE_PRIVATE)
                    val na = "na"
                    wb.text = na
                    wp.text = na
                    val editor = preferences.edit()
                    val editorlog = preferenceslog.edit()
                    editor.putString("wardlogin", wb.text.toString())
                    editorlog.putString("wardpass", wp.text.toString())
                    editor.apply()
                    editorlog.apply()
                }
        }

        private fun registerUser(
            email: String,
            name: String,
            password: String
        ) {
            compositeDisposable.add(
                iMyService.registerUser(email, name, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { response ->
                        Toast.makeText(
                            this@MainActivity,
                            "" + response,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            )
        }

        override fun onBackPressed() {
            super.onBackPressed()
            overridePendingTransition(
                R.anim.nav_default_pop_enter_anim,
                R.anim.nav_default_pop_exit_anim
            )
        }

        fun txt_create_account_ward(view: View) {
            val retrofitClient: Retrofit = RetrofitClient.getInstance()
            iMyService = retrofitClient.create(IMyService::class.java)
            val txt_create_account: TextView = findViewById(R.id.wardregisters)
            val register_layout: View = LayoutInflater.from(this@MainActivity)
                .inflate(R.layout.activity_register, null)
            txt_create_account.paintFlags =
                txt_create_account.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            MaterialStyledDialog.Builder(this@MainActivity)
                .setIcon(R.drawable.paina_logo)
                .setTitle("REGISTRATION")
                .setDescription("Please fill all fields")
                .setCustomView(register_layout)
                .setNegativeText("CANCEL")
                .onNegative { dialog, _ -> dialog.dismiss() }
                .setPositiveText("REGISTER")
                .onPositive(object : MaterialDialog.SingleButtonCallback {
                    override fun onClick(dialog: MaterialDialog, which: DialogAction) {
                        val edt_register_email: MaterialEditText =
                            register_layout.findViewById(R.id.edtusername)
                        val edt_register_password: MaterialEditText =
                            register_layout.findViewById(R.id.edtpassword)
                        val edt_register_name: MaterialEditText =
                            register_layout.findViewById(R.id.edtname)
                        if (TextUtils.isEmpty(edt_register_email.text.toString())) {
                            Toast.makeText(
                                this@MainActivity,
                                "Email cannot be empty",
                                Toast.LENGTH_SHORT
                            ).show()
                            return
                        }
                        if (TextUtils.isEmpty(edt_register_name.text.toString())) {
                            Toast.makeText(
                                this@MainActivity,
                                "Name cannot be empty",
                                Toast.LENGTH_SHORT
                            ).show()
                            return
                        }
                        if (TextUtils.isEmpty(edt_register_password.text.toString())) {
                            Toast.makeText(
                                this@MainActivity,
                                "Password cannot be empty",
                                Toast.LENGTH_SHORT
                            ).show()
                            return
                        }
                        registerUser(
                            edt_register_email.text.toString(),
                            edt_register_name.text.toString(),
                            edt_register_password.text.toString()
                        )
                    }
                }).show()
        }

        fun wardlogout(view: View) {
            val buttonregister: Button = findViewById(R.id.buttonregister)
            val registers: TextView = findViewById(R.id.registers)
            val wardlin: LinearLayout = findViewById(R.id.wardlin)
            val wardfirst: LinearLayout = findViewById(R.id.wardfirst)
            val wardthird: LinearLayout = findViewById(R.id.wardthird)
            val wardpointlayout: LinearLayout = findViewById(R.id.wardpointlayout)
            val wardwelcome: TextView = findViewById(R.id.wardwelcome)
            val wardpoints: TextView = findViewById(R.id.wardpoints)
            buttonregister.visibility = View.VISIBLE
            registers.visibility = View.VISIBLE
            wardlin.visibility = View.VISIBLE
            wardfirst.visibility = View.VISIBLE
            wardthird.visibility = View.VISIBLE
            wardpointlayout.visibility = View.GONE
            val wb: TextView = findViewById(R.id.wardbox)
            val wp: TextView = findViewById(R.id.wardpass)
            val preferences = getSharedPreferences("wardlog", Context.MODE_PRIVATE)
            val preferenceslog = getSharedPreferences("wardpas", Context.MODE_PRIVATE)
            val na = "na"
            wb.text = na
            wp.text = na
            val editor = preferences.edit()
            val editorlog = preferenceslog.edit()
            editor.putString("wardlogin", wb.text.toString())
            editorlog.putString("wardpass", wp.text.toString())
            editor.apply()
            editorlog.apply()
            setContentView(R.layout.fragment_account)
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.wardaccount, registerfragment())
            fragmentTransaction.commit()
        }

        fun back(view: View) {
            val v: TextView = findViewById(R.id.vh)
            val preferences = getSharedPreferences("regi", Context.MODE_PRIVATE)
            val na = "na"
            v.text = na
            val editor = preferences.edit()
            editor.putString("region", v.text.toString())
            editor.apply()
            val back = Intent(this@MainActivity, ward_center::class.java)
            startActivity(back)
        }

        fun openfacebooklink(view: View) {
            val facebook_link = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.facebook.com/pages/Paina-Cafe/138936765557")
            )
            startActivity(facebook_link)
        }

        fun openinstagramlink(view: View) {
            val instagram_link = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://instagram.com/painacafe?ref=badge")
            )
            startActivity(instagram_link)
        }

        fun openyelplink(view: View) {
            val yelp_link = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.yelp.com/biz/paina-cafe-honolulu")
            )
            startActivity(yelp_link)
        }

        fun ward_phone(view: View) {
            val textview10: TextView = findViewById(R.id.textView10)
            textview10.paintFlags = textview10.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            textview10.paintFlags = textview10.paintFlags xor Paint.UNDERLINE_TEXT_FLAG
            val ward_phone = Intent(
                Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(8083562829.toString()))
            )
            startActivity(ward_phone)
        }
    }