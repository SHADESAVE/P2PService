package com.example.myapplication.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.api.Api
import com.example.myapplication.response.BaseResponse
import kotlinx.android.synthetic.main.autorization.*
import kotlinx.android.synthetic.main.zaem_balancefill.*
import kotlinx.android.synthetic.main.zaem_cabinet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ZaemFillBalanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.zaem_balancefill)
        button_fillAndReturn.setOnClickListener{
            val intent = Intent(this, ZaemCabinetActivity::class.java)
            var valueFill : Double = editText5.text.toString().toDouble()

            if (AutorizationActivity.GlobalVariable.flag == 1) {

                val apiService = Api.create()
                apiService.fillBalanceInvestor(AutorizationActivity.GlobalVariable.UUID, valueFill).enqueue(object : Callback<BaseResponse<Double>> {
                    override fun onFailure(call: Call<BaseResponse<Double>>, t: Throwable) {
                    }
                    override fun onResponse(call: Call<BaseResponse<Double>>, response: Response<BaseResponse<Double>>) {
                    }
                })
            }
            else {
                val apiService = Api.create()
                apiService.fillBalanceBorrower(AutorizationActivity.GlobalVariable.UUID, valueFill).enqueue(object : Callback<BaseResponse<Double>> {
                    override fun onFailure(call: Call<BaseResponse<Double>>, t: Throwable) {
                    }
                    override fun onResponse(call: Call<BaseResponse<Double>>, response: Response<BaseResponse<Double>>) {
                    }
                })
            }
            val text = "Пополнено!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
            startActivity(intent)
        }

    }
}
