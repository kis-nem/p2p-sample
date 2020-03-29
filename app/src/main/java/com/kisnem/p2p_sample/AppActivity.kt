package com.kisnem.p2p_sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        openClientFragment()
    }

    private fun openClientFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_container, ClientFragment.newInstance())
            addToBackStack(null)
            commit()
        }
    }
}
