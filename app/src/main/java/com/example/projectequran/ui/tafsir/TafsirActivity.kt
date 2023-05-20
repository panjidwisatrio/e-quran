package com.example.projectequran.ui.tafsir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.projectequran.databinding.ActivityTafsirBinding
import com.example.projectequran.util.Constanta.EXTRA_NAMA_LATIN
import com.example.projectequran.util.Constanta.EXTRA_NOMOR_AYAT
import com.example.projectequran.util.Constanta.EXTRA_TAFSIR

class TafsirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTafsirBinding
    private var tafsir: String? = ""
    private var nomorAyat: Int? = 0
    private var namaSurat: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTafsirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = null
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tafsir = intent.getStringExtra(EXTRA_TAFSIR)
        nomorAyat = intent.getIntExtra(EXTRA_NOMOR_AYAT, 0)
        namaSurat = intent.getStringExtra(EXTRA_NAMA_LATIN)

        binding.apply {
            tvTafsirText.text = tafsir
            tvTafsirAyat.text = nomorAyat.toString()
            tvTafsirNamaSurat.text = namaSurat
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        tafsir?.let { outState.putString(EXTRA_TAFSIR, it) }
        nomorAyat?.let { outState.putInt(EXTRA_NOMOR_AYAT, it) }
        namaSurat?.let { outState.putString(EXTRA_NAMA_LATIN, it) }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        tafsir = savedInstanceState.getString(EXTRA_TAFSIR)
        nomorAyat = savedInstanceState.getInt(EXTRA_NOMOR_AYAT)
        namaSurat = savedInstanceState.getString(EXTRA_NAMA_LATIN)
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}