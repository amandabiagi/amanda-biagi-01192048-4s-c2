package com.example.meuamigoco

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

    lateinit var  etId01:EditText;
    lateinit var  etId02:EditText;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etId01 = findViewById(R.id.et_id01)
        etId02 = findViewById(R.id.et_id02)

    }

    fun comprar(view: View) {
        val apiCachorros = ConexaoApiCachorros.criar()
        val id01 = etId01.text.toString().toInt()
        val id02 = etId02.text.toString().toInt()
        val cachorros = mutableListOf<Cachorro>()

        apiCachorros.get(id01).enqueue(object : Callback<Cachorro>{
            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>){
                val cachorro = response.body()
                if(cachorro != null){
                    cachorros.add(cachorro)
                }

            }override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(baseContext, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        apiCachorros.get(id02).enqueue(object : Callback<Cachorro>{
            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>){

                val cachorro = response.body()

                if(cachorro != null){
                    cachorros.add(cachorro)
                }

            }override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(baseContext, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        if(cachorros.isEmpty()){
            val telaSemCachorro = Intent(this, TelaSemCachorro::class.java)
            telaSemCachorro.putExtra("primeiro_id",id01)
            telaSemCachorro.putExtra("segundo_id",id02)
            startActivity(telaSemCachorro)
        }else
        {
            val telaComCachorro = Intent(this, TelaComCachorro::class.java)
            if(cachorros.size == 2) {
                telaComCachorro.putExtra("cachorro_01_raca", cachorros.get(0).raca)
                telaComCachorro.putExtra("cachorro_01_valor", cachorros.get(0).precoMedio)

                telaComCachorro.putExtra("cachorro_02_raca", cachorros.get(1).raca)
                telaComCachorro.putExtra("cachorro_02_valor", cachorros.get(1).precoMedio)

                startActivity(telaComCachorro)
            }

        }


    }
}