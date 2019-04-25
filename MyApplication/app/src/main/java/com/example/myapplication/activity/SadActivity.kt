package com.example.myapplication.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.example.myapplication.R

import kotlinx.android.synthetic.main.sad_activity.*
import kotlinx.android.synthetic.main.zaem_cabinet.*

class SadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sad_activity)
        button_backto.setOnClickListener {
            val intent = Intent(this, ZaemCabinetActivity::class.java)
            startActivity(intent)
        }
    }

}
