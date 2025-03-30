package br.edu.utfpr.fueconsumption

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etConsumo1 : EditText
    private lateinit var etConsumo2 : EditText
    private lateinit var etValor1 : EditText
    private lateinit var etValor2 : EditText
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etConsumo1 = findViewById(R.id.etConsumo1)
        etConsumo2 = findViewById( R.id.etConsumo2 )
        etValor1 = findViewById( R.id.etValor1 )
        etValor2 = findViewById( R.id.etValor2 )
        tvResultado = findViewById(R.id.tvResultado)

        val btnCalcular: Button = findViewById(R.id.btCalcular)
        btnCalcular.setOnClickListener {
            calcularMelhorCombustivel()
        }
    }

    private fun calcularMelhorCombustivel() {
        val consumo1 = etConsumo1.text.toString()
        val consumo2 = etConsumo2.text.toString()
        val valor1 = etValor1.text.toString()
        val valor2 = etValor2.text.toString()

        if (consumo1.isEmpty() || consumo2.isEmpty() || valor1.isEmpty() || valor2.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            return
        }

        val consumo1Double = consumo1.toDouble()
        val consumo2Double = consumo2.toDouble()
        val valor1Double = valor1.toDouble()
        val valor2Double = valor2.toDouble()

        val custoComb1 = valor1Double / consumo1Double
        val custoComb2 = valor2Double / consumo2Double

        val melhorCombustivel = if (custoComb1 < custoComb2) {
            "Melhor combustível: Combustivel 1 (Custo por km: R$ ${"%.2f".format(custoComb1)})"
        } else {
            "Melhor combustível: Combustivel 2 (Custo por km: R$ ${"%.2f".format(custoComb2)})"
        }

        tvResultado.text = melhorCombustivel
    }

    fun btListar1OnClick(view: View) {
        val intent = Intent(this, ListarActivity::class.java)
        getResult1.launch(intent)
    }

    fun btListar2OnClick(view: View) {
        val intent = Intent(this, ListarActivity::class.java)
        getResult2.launch(intent)
    }

    private val getResult1 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val codRetorno = it.data?.getIntExtra("codRetorno", 0)
            etConsumo1.setText("$codRetorno")
        }
    }

    private val getResult2 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val codRetorno = it.data?.getIntExtra("codRetorno", 0)
            etConsumo2.setText("$codRetorno")
        }
    }

    fun btLimparOnClick(view: View) {
        etValor1.setText("")
        etValor2.setText("")
        etConsumo1.setText("")
        etConsumo2.setText("")
        tvResultado.setText("Resultado: ")
    }
}