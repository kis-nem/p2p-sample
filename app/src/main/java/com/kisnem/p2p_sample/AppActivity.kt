package com.kisnem.p2p_sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        openAppFragment()
    }

    private fun openAppFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_container, AppFragment.newInstance())
            addToBackStack("app")
            commit()
        }
    }
}
