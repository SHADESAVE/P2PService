package com.example.myapplication.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.api.Api
import com.example.myapplication.response.AuthResponse
import com.example.myapplication.response.BaseResponse
import kotlinx.android.synthetic.main.autorization.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AutorizationActivity : AppCompatActivity() {

    object GlobalVariable {
        var UUID : String = ""
        var nName: String = ""
        var flag = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.autorization)
        val intent = Intent(this, ZaemCabinetActivity::class.java)
        button_autorization.setOnClickListener {
            val etL: String = login.text.toString()
            val etP: String = password.text.toString()

            GlobalVariable.flag =0
            GlobalVariable.nName = etL

            textViewErrLogin.text=""
            textViewErrPass.text=""
            if(etL.isEmpty() or etP.isEmpty()) {
                if(etL.isEmpty()) {
                    textViewErrLogin.text = " Логин не указан"
                }
                if(etP.isEmpty()) {
                    textViewErrPass.text = " Пароль не указан"
                }
            }
            else {

                if (checkBox2.isChecked) {

                    GlobalVariable.flag = 1
                    val apiService = Api.create()
                    apiService.login(etL, etP, "true").enqueue(object : Callback<BaseResponse<String>> {
                        override fun onFailure(call: Call<BaseResponse<String>>, t: Throwable) {
                            Log.d("asd", t.message)
                        }

                        override fun onResponse(call: Call<BaseResponse<String>>, response: Response<BaseResponse<String>>) {
                            val data = response.body()?.data

                            if (response.body()?.status == true && data != null) {
                                GlobalVariable.UUID = data
                                startActivity(intent)
                            }
                            else {
                                textViewErrPass.text = " Неверный логин или пароль"
                            }
                        }
                    })


                }
                else {
                    val apiService = Api.create()
                    apiService.login(etL, etP, "false").enqueue(object : Callback<BaseResponse<String>> {
                        override fun onFailure(call: Call<BaseResponse<String>>, t: Throwable) {
                            Log.d("asd", t.message)
                        }

                        override fun onResponse(call: Call<BaseResponse<String>>, response: Response<BaseResponse<String>>) {
                            val data2 = response.body()?.data

                            if (response.body()?.status==true && data2 != null) {
                                GlobalVariable.UUID= data2
                                startActivity(intent)
                            }
                            else {
                                textViewErrPass.text = " Неверный логин или пароль"
                            }
                        }
                    })
                }

            }
        }
    }
}
