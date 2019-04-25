package com.example.myapplication.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.api.Api
import com.example.myapplication.response.AuthResponse
import com.example.myapplication.response.BaseResponse
import kotlinx.android.synthetic.main.zaem_zayavka.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ZaemCreateOfferActivity : AppCompatActivity() {
    object GlobalVariable2 {
        var offerId : String = ""
        var termite : Int? = 0
        var summa : Double? = 0.0
        var persent : Double? = 0.0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.zaem_zayavka)

        button_sendAndReturn3.setOnClickListener {
            //Написать отправку на сервер
            val intent = Intent(this, ZaemCreateOffer2Activity::class.java)
            val intenet = Intent(this, SadActivity::class.java)

            var sum : Double = editText.text.toString().toDouble()
            var term : Int = editText4.text.toString().toInt()

            val apiService = Api.create()
            apiService.borrowerAsk(AutorizationActivity.GlobalVariable.UUID, sum, term).enqueue(object : Callback<BaseResponse<AuthResponse>> {
                override fun onFailure(call: Call<BaseResponse<AuthResponse>>, t: Throwable) {
                }
                override fun onResponse(call: Call<BaseResponse<AuthResponse>>, response: Response<BaseResponse<AuthResponse>>) {
                    val data5 = response.body()?.data?.id
                    val termit = response.body()?.data?.term
                    val sumaot = response.body()?.data?.sum
                    val peres = response.body()?.data?.percent
                    if(response.body()?.status == true && data5 != null) {
                        GlobalVariable2.offerId = data5
                        GlobalVariable2.termite = termit
                        GlobalVariable2.summa = sumaot
                        GlobalVariable2.persent = peres
                        startActivity(intent)
                    }
                    else {
                        startActivity(intenet)
                    }
                }
            })

        }
        button_sendAndReturn2.setOnClickListener {
            val intent = Intent(this, ZaemCabinetActivity::class.java)
            startActivity(intent)
        }

    }
}
