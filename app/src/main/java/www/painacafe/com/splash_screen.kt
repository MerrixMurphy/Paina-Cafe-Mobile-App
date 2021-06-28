package www.painacafe.com

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class splash_screen : AppCompatActivity() {

    private val SPLASH_TIME = 1000
    private var ProgressStatus = 0
    private val Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        overridePendingTransition(R.anim.nav_default_pop_enter_anim, R.anim.nav_default_pop_exit_anim)

        supportActionBar?.hide()

        val ProgressBar: ProgressBar = findViewById(R.id.progressBar)
        val LoadingText: TextView = findViewById(R.id.textView)


        Thread(Runnable {
            while (ProgressStatus < 100) {
                ProgressStatus++
                SystemClock.sleep(10)
                Handler.post { ProgressBar.progress = ProgressStatus }
            }
            Handler.post { LoadingText.visibility = View.VISIBLE }
        }).start()

        Handler().postDelayed({
            val Splash = Intent(this@splash_screen, ward_center::class.java)
            startActivity(Splash)
            finish()
        }, SPLASH_TIME.toLong())
    }
}