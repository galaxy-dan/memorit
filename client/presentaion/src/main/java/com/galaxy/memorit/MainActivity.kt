package com.galaxy.memorit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.galaxy.memorit.ui.theme.PartylogTheme
import com.galaxy.memorit.util.PartylogApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PartylogTheme {
                PartylogApp()
            }
        }
    }
}