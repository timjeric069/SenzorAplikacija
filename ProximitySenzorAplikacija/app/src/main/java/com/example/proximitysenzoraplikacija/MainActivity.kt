package com.example.proximitysenzoraplikacija

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proximitysenzoraplikacija.ui.theme.ProximitySenzorAplikacijaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProximitySenzorAplikacijaTheme {
                val viewModel = viewModel<Viewmodel>()
                val distance = viewModel.distance.value
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(if (distance == 5f) Color.Black else Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if(distance == 5f) {
                            "Far " + distance + " cm"
                        } else {
                            "Near "+ distance + " cm"
                        },
                        color = if(distance == 5f) Color.White else Color.DarkGray,
                        fontSize = 24.sp

                    )

                }
            }
        }
    }
}