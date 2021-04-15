package com.example.meuamigoco

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TelaSemCachorro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_sem_cachorro)

        val id1 = intent.getStringExtra("primeiro_id")
        val id2 = intent.getStringExtra("segundo_id")

        val tvFrase:TextView = findViewById(R.id.tv_frase)

        tvFrase.text = getString(R.string.deu_ruim, id1, id2)


    }
}