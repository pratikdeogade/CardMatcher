package com.pi.cardmatcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pi.cardmatcher.ui.CardMatcherFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    CardMatcherFragment.newInstance(),
                    CardMatcherFragment::class.java.getName()
                )
                .commitNow()
        }
    }
}