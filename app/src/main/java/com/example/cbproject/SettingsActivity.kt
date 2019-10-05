@file:Suppress("DEPRECATION")

package com.example.cbproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.R
import android.annotation.SuppressLint
import android.preference.PreferenceFragment
import android.preference.PreferenceActivity


class SettingsActivity : PreferenceActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentManager.beginTransaction().replace(android.R.id.content, SettingsFragment()).commit()
    }

     public class SettingsFragment : PreferenceFragment() {
        @SuppressLint("ResourceType")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(com.example.cbproject.R.layout.preferences)
        }
    }
}