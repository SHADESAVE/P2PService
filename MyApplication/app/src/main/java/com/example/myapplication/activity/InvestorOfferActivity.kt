package com.example.myapplication.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.api.Api
import com.example.myapplication.response.Auth2Response
import com.example.myapplication.response.BaseResponse
import kotlinx.android.synthetic.main.investor_zayavka.*
import kotlinx.android.synthetic.main.zaem_cabinet.*
import kotlinx.android.synthetic.main.zaem_zayavka.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InvestorOfferActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.investor_zayavka)

        button_create.setOnClickListener {
            var sum : Double = editText2.text.toString().toDouble()
            val apiService = Api.create()
            apiService.investorOffer(AutorizationActivity.GlobalVariable.UUID, sum).enqueue(object : Callback<BaseResponse<Auth2Response>> {
                override fun onFailure(call: Call<BaseResponse<Auth2Response>>, t: Throwable) {
                    Log.d("asd", t.message)
                }
                override fun onResponse(call: Call<BaseResponse<Auth2Response>>, response: Response<BaseResponse<Auth2Response>>) {
                    val per : String = response.body()?.data?.percent.toString()
                    val text = "Инвестировано под " + per + "%"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()
                }
            })
            val intent = Intent(this, ZaemCabinetActivity::class.java)
            startActivity(intent)
        }
        button_back.setOnClickListener {
            val intent = Intent(this, ZaemCabinetActivity::class.java)
            startActivity(intent)
        }
    }
}
