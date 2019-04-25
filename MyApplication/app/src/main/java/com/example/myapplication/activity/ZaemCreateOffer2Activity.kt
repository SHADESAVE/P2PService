package com.example.myapplication.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.api.Api
import com.example.myapplication.response.AuthResponse
import com.example.myapplication.response.BaseResponse
import kotlinx.android.synthetic.main.zaem_predloj.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ZaemCreateOffer2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.zaem_predloj)
        textView8.text = ZaemCreateOfferActivity.GlobalVariable2.summa.toString()
        textView9.text = ZaemCreateOfferActivity.GlobalVariable2.persent.toString()
        textView11.text = ZaemCreateOfferActivity.GlobalVariable2.termite.toString()
        val intent = Intent(this, ZaemCabinetActivity::class.java)

        button_acceptOr.setOnClickListener {
            val apiService = Api.create()
            apiService.borrowerOk(ZaemCreateOfferActivity.GlobalVariable2.offerId, AutorizationActivity.GlobalVariable.UUID).enqueue(object :
                Callback<BaseResponse<Boolean>> {
                override fun onFailure(call: Call<BaseResponse<Boolean>>, t: Throwable) {
                }
                override fun onResponse(call: Call<BaseResponse<Boolean>>, response: Response<BaseResponse<Boolean>>) {
                    val data5 = response.body()?.data
                    if(response.body()?.status == true && data5 != null) {
                        val text = "Одобрено!"
                        val duration = Toast.LENGTH_SHORT
                        val toast = Toast.makeText(applicationContext, text, duration)
                        toast.show()
                    }
                    else {
                        val text = "Не одобрено!"
                        val duration = Toast.LENGTH_SHORT
                        val toast = Toast.makeText(applicationContext, text, duration)
                        toast.show()
                    }
                }
            })
            startActivity(intent)
        }

        button_acceptOr2.setOnClickListener{
            startActivity(intent)
        }
    }
}
