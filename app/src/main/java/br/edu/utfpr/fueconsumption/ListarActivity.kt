package br.edu.utfpr.fueconsumption

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListarActivity : AppCompatActivity() {

    private lateinit var lvCombustiveis : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)

        lvCombustiveis = findViewById( R.id.lvCombustiveis)

        lvCombustiveis.setOnItemClickListener{ parent, view, position, id ->
            val itemSelecionadoId = if (position == 0) 9 else 12

            intent.putExtra("codRetorno", itemSelecionadoId)

            setResult(RESULT_OK, intent)

            finish()

        }
    }
}