package com.example.findip

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.text.format.Formatter
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textip=findViewById<TextView>(R.id.textIp)
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val fab=findViewById<FloatingActionButton>(R.id.fab)


        wifiManager.setWifiEnabled(true)
        if(wifiManager.connectionInfo.networkId!=-1)
        {
            fab.visibility=View.GONE
            Toast.makeText(this, "Wifi Enabled & Connected", Toast.LENGTH_SHORT).show()
            val ipAddress: String = Formatter.formatIpAddress(wifiManager.connectionInfo.ipAddress)
            textip.setText("Current Wi-Fi IP Address is:- "+ipAddress)

        }
        else
        {
            textip.setText("Not Connected To Wi-fi \nSo Click on Below Button \nto Restart App")
            fab.visibility=View.VISIBLE
            fab.setOnClickListener {
                val i = baseContext.packageManager
                    .getLaunchIntentForPackage(baseContext.packageName)
                i!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(i)
                finish()
            }

        }


    }
}
