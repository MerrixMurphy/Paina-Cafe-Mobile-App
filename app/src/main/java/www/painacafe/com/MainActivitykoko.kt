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


class MainActivitykoko : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_koko)
        overridePendingTransition(R.anim.nav_default_pop_enter_anim, R.anim.nav_default_pop_exit_anim)

        supportActionBar?.hide()

        val navView: BottomNavigationView = findViewById(R.id.nav_view_koko)

        val navController = findNavController(R.id.nav_host_fragment_koko)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home_koko, R.id.navigation_dashboard_koko, R.id.navigation_notifications_koko
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.nav_default_pop_enter_anim, R.anim.nav_default_pop_exit_anim)
    }

    fun back(view: View) {
        val v: TextView = findViewById(R.id.vk)
        val preferences = getSharedPreferences("regi", Context.MODE_PRIVATE)
        val na = "na"
        v.text = na
        val editor = preferences.edit()
        editor.putString("region",v.text.toString())
        editor.apply()
        val back = Intent(this@MainActivitykoko, ward_center::class.java)
        startActivity(back)
    }

    fun openfacebooklinkkoko() {
        val facebook_link_koko = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.facebook.com/pages/Paina-Cafe/138936765557"))
        startActivity(facebook_link_koko)
    }

    fun openinstagramlinkkoko() {
        val instagram_link_koko = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://instagram.com/painacafe?ref=badge"))
        startActivity(instagram_link_koko)
    }
    fun openyelplinkkoko() {
        val yelp_link_koko = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.yelp.com/biz/paina-cafe-honolulu"))
        startActivity(yelp_link_koko)
    }
    fun koko_phone() {
        val textview10: TextView = findViewById(R.id.textView10)
        textview10.paintFlags = textview10.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        val koko_phone = Intent(
            Intent.ACTION_DIAL, Uri.parse("tel:"+Uri.encode(8083562829.toString())))
        startActivity(koko_phone)
    }
}
