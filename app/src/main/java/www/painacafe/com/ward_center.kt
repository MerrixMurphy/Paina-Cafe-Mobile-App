package www.painacafe.com

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ward_center : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_center)
        overridePendingTransition(
            R.anim.nav_default_pop_enter_anim,
            R.anim.nav_default_pop_exit_anim
        )

        supportActionBar?.hide()
        val time = 0
        val open = Runnable {
            val v: TextView = findViewById(R.id.v)
            val preferences = getSharedPreferences("regi", Context.MODE_PRIVATE)
            val re = preferences.getString("region", "defaultValue")
            v.text = re
            val editor = preferences.edit()
            editor.putString("region",v.text.toString())
            editor.apply()
            if (re == "vegas"){
                Handler().postDelayed({
                    val vegas = Intent(this@ward_center, MainActivityvegas::class.java)
                    startActivity(vegas)
                    finish()
                }, time.toLong())
            }
            if (re == "ward"){
                Handler().postDelayed({
                    val vegas = Intent(this@ward_center, MainActivity::class.java)
                    startActivity(vegas)
                    finish()
                }, time.toLong())
            }
            if (re == "koko"){
                Handler().postDelayed({
                    val vegas = Intent(this@ward_center, MainActivitykoko::class.java)
                    startActivity(vegas)
                    finish()
                }, time.toLong())
            }
        }
        open.run()

        val ward: ImageView = findViewById(R.id.wardmap)
        ward.setOnClickListener {
            val Ward2 = Intent(this@ward_center, MainActivity::class.java)
            startActivity(Ward2)
            val v: TextView = findViewById(R.id.v)
            val wa = "ward"
            v.text = wa
            val preferences = getSharedPreferences("regi", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("region", v.text.toString())
            editor.apply()
        }

        val koko: ImageView = findViewById(R.id.koko)
        koko.setOnClickListener {
            val koko2 = Intent(this@ward_center, MainActivitykoko::class.java)
            startActivity(koko2)
            val v: TextView = findViewById(R.id.v)
            val ko = "koko"
            v.text = ko
            val preferences = getSharedPreferences("regi", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("region", v.text.toString())
            editor.apply()
        }

        val vegas: ImageView = findViewById(R.id.lasvegas)
        vegas.setOnClickListener {
            val vegas2 = Intent(this@ward_center, MainActivityvegas::class.java)
            startActivity(vegas2)
            val v: TextView = findViewById(R.id.v)
            val veg = "vegas"
            v.text = veg
            val preferences = getSharedPreferences("regi", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("region", v.text.toString())
            editor.apply()
        }
    }
}
