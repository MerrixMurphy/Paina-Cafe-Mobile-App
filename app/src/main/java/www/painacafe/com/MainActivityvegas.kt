package www.painacafe.com

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivityvegas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_vegas)
        overridePendingTransition(R.anim.nav_default_pop_enter_anim, R.anim.nav_default_pop_exit_anim)

        supportActionBar?.hide()

        val navView: BottomNavigationView = findViewById(R.id.nav_view_vegas)

        val navController = findNavController(R.id.nav_host_fragment_vegas)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home_vegas, R.id.navigation_dashboard_vegas, R.id.navigation_notifications_vegas
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.nav_default_pop_enter_anim,
            R.anim.nav_default_pop_exit_anim
        )
    }

    fun back(view: View) {
        val v: TextView = findViewById(R.id.vo)
        val preferences = getSharedPreferences("regi", Context.MODE_PRIVATE)
        val na = "na"
        v.text = na
        val editor = preferences.edit()
        editor.putString("region",v.text.toString())
        editor.apply()
        val back = Intent(this@MainActivityvegas, ward_center::class.java)
        startActivity(back)
            }

    fun openfacebooklinkvegas() {
        val facebook_link_vegas = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.facebook.com/pages/Paina-Cafe/138936765557"))
        startActivity(facebook_link_vegas)
    }

    fun openinstagramlinkvegas() {
        val instagram_link_vegas = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://instagram.com/painacafe?ref=badge"))
        startActivity(instagram_link_vegas)
    }
    fun openyelplinkvegas() {
        val yelp_link_vegas = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.yelp.com/biz/paina-cafe-honolulu"))
        startActivity(yelp_link_vegas)
    }
    fun vegas_phone() {
        val textview10: TextView = findViewById(R.id.textView10)
        textview10.paintFlags = textview10.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        val vegas_phone = Intent(
            Intent.ACTION_DIAL, Uri.parse("tel:"+Uri.encode(7022722790.toString())))
        startActivity(vegas_phone)
    }

}
