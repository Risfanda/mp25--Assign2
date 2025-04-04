package unram.psti.helloapp

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.PI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi View
        val spinner = findViewById<Spinner>(R.id.spinnerBangunDatar)
        val etInput1 = findViewById<EditText>(R.id.etInput1)
        val etInput2 = findViewById<EditText>(R.id.etInput2)
        val btnHitung = findViewById<Button>(R.id.btnHitung)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        // Daftar bangun datar
        val bangunDatar = arrayOf("Persegi Panjang", "Lingkaran", "Segitiga")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bangunDatar)
        spinner.adapter = adapter

        // Saat bangun datar dipilih, atur input yang diperlukan
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> { // Persegi Panjang
                        etInput1.hint = "Masukkan Panjang"
                        etInput2.visibility = View.VISIBLE
                        etInput2.hint = "Masukkan Lebar"
                    }
                    1 -> { // Lingkaran
                        etInput1.hint = "Masukkan Jari-jari"
                        etInput2.visibility = View.GONE
                    }
                    2 -> { // Segitiga
                        etInput1.hint = "Masukkan Alas"
                        etInput2.visibility = View.VISIBLE
                        etInput2.hint = "Masukkan Tinggi"
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Tombol Hitung
        btnHitung.setOnClickListener {
            val input1 = etInput1.text.toString().toDoubleOrNull()
            val input2 = etInput2.text.toString().toDoubleOrNull()

            if (input1 != null && (input2 != null || spinner.selectedItemPosition == 1)) {
                val hasil = when (spinner.selectedItemPosition) {
                    0 -> { // Persegi Panjang
                        val luas = input1 * (input2 ?: 0.0)
                        val keliling = 2 * (input1 + (input2 ?: 0.0))
                        "Luas: $luas \nKeliling: $keliling"
                    }
                    1 -> { // Lingkaran
                        val luas = PI * input1 * input1
                        val keliling = 2 * PI * input1
                        "Luas: $luas \nKeliling: $keliling"
                    }
                    2 -> { // Segitiga
                        val luas = 0.5 * input1 * (input2 ?: 0.0)
                        "Luas: $luas (Keliling tidak dihitung)"
                    }
                    else -> "Error"
                }
                tvHasil.text = hasil
            } else {
                tvHasil.text = "Masukkan angka yang valid!"
            }
        }
    }
}
