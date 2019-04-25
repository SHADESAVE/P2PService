package com.example.myapplication.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.api.Api
import com.example.myapplication.response.BaseResponse
import kotlinx.android.synthetic.main.autorization.*
import kotlinx.android.synthetic.main.zaem_cabinet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ZaemCabinetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.zaem_cabinet)
        textView4.text = AutorizationActivity.GlobalVariable.nName

        if (AutorizationActivity.GlobalVariable.flag == 1) {
            textView.text = "Инвестор:"
            val apiService = Api.create()
            apiService.viewBalanceInvestor(AutorizationActivity.GlobalVariable.UUID).enqueue(object : Callback<BaseResponse<Double>> {
                override fun onFailure(call: Call<BaseResponse<Double>>, t: Throwable) {
                    Log.d("asd", t.message)
                }

                override fun onResponse(call: Call<BaseResponse<Double>>, response: Response<BaseResponse<Double>>) {
                    val data3 = response.body()?.data

                    if (response.body()?.status == true && data3 != null) {
                        textViewSumm.text = data3.toString()
                    }
                    else {
                        textViewSumm.text = "Ошибка статус = фалсе"
                    }
                }
            })
            button_createOffer.setOnClickListener {
                val intent = Intent(this, InvestorOfferActivity::class.java)
                startActivity(intent)
            }
        }
        else {
            val apiService = Api.create()
            apiService.viewBalanceBorrower(AutorizationActivity.GlobalVariable.UUID).enqueue(object : Callback<BaseResponse<Double>> {
                override fun onFailure(call: Call<BaseResponse<Double>>, t: Throwable) {
                    Log.d("asd", t.message)
                }

                override fun onResponse(call: Call<BaseResponse<Double>>, response: Response<BaseResponse<Double>>) {
                    val data3 = response.body()?.data

                    if (response.body()?.status == true && data3 != null) {
                        textViewSumm.text = data3.toString()
                    }
                    else {
                        textViewSumm.text = "Ошибка статус = фалсе"
                    }
                }
            })
            button_createOffer.setOnClickListener {
                val intent = Intent(this, ZaemCreateOfferActivity::class.java)
                startActivity(intent)
            }
        }
        button_fillBalance.setOnClickListener {
            val intent = Intent(this, ZaemFillBalanceActivity::class.java)
            startActivity(intent)
        }

        button_logOut.setOnClickListener {
            val intent = Intent(this, AutorizationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
