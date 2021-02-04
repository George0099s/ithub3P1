package com.ithub.lesson2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_activty)

        if (savedInstanceState == null)
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("frag")
            .replace(R.id.frag_cont, BlankFragment(), "frag")
            .commit()
    }

    override fun onBackPressed() {
        Log.d(Companion.TAG, "onBackPressed: ${supportFragmentManager.backStackEntryCount}")
        super.onBackPressed()
    }

    companion object {
        private const val TAG = "FragmentActivity"
    }
}