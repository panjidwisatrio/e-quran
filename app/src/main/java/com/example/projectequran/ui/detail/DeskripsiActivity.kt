package com.example.projectequran.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.projectequran.databinding.ActivityDeskripsiBinding
import com.example.projectequran.util.Constanta.EXTRA_ARTI
import com.example.projectequran.util.Constanta.EXTRA_DESKRIPSI
import com.example.projectequran.util.Constanta.EXTRA_JUMLAH_AYAT
import com.example.projectequran.util.Constanta.EXTRA_NAMA_LATIN
import com.example.projectequran.util.Constanta.EXTRA_TEMPAT_TURUN

class DeskripsiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeskripsiBinding
    private var namaSurat: String? = ""
    private var artiSurat: String? = ""
    private var tempatTurun: String? = ""
    private var jumlahAyat: String? = ""
    private var deskripsi: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeskripsiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = null
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        namaSurat = intent.getStringExtra(EXTRA_NAMA_LATIN)
        artiSurat = intent.getStringExtra(EXTRA_ARTI)
        tempatTurun = intent.getStringExtra(EXTRA_TEMPAT_TURUN)
        jumlahAyat = intent.getStringExtra(EXTRA_JUMLAH_AYAT)
        deskripsi = intent.getStringExtra(EXTRA_DESKRIPSI)
        val enhancedDeskripsi = deskripsi?.replace("<i>", "")
        val enhancedDeskripsi2 = enhancedDeskripsi?.replace("</i>", "")

        binding.apply {
            tvDeskripsiNamaSurat.text = namaSurat
            tvDeskripsiArtiSurat.text = artiSurat
            tvDeskripsiTempatTurun.text = tempatTurun
            tvDeskripsiJumlahAyat.text = jumlahAyat
            tvDeskripsiText.text = enhancedDeskripsi2
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        namaSurat?.let { outState.putString(EXTRA_NAMA_LATIN, it) }
        artiSurat?.let { outState.putString(EXTRA_ARTI, it) }
        tempatTurun?.let { outState.putString(EXTRA_TEMPAT_TURUN, it) }
        jumlahAyat?.let { outState.putString(EXTRA_JUMLAH_AYAT, it) }
        deskripsi?.let { outState.putString(EXTRA_DESKRIPSI, it) }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        namaSurat = savedInstanceState.getString(EXTRA_NAMA_LATIN)
        artiSurat = savedInstanceState.getString(EXTRA_ARTI)
        tempatTurun = savedInstanceState.getString(EXTRA_TEMPAT_TURUN)
        jumlahAyat = savedInstanceState.getString(EXTRA_JUMLAH_AYAT)
        deskripsi = savedInstanceState.getString(EXTRA_DESKRIPSI)
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