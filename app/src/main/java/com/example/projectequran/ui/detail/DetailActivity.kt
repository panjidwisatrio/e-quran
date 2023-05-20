package com.example.projectequran.ui.detail

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.AudioAttributes
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.projectequran.R
import com.example.projectequran.data.Resource
import com.example.projectequran.databinding.ActivityDetailBinding
import com.example.projectequran.model.Surat
import com.example.projectequran.ui.adapter.SectionTabAdapter
import com.example.projectequran.util.Constanta.EXTRA_ARTI
import com.example.projectequran.util.Constanta.EXTRA_DESKRIPSI
import com.example.projectequran.util.Constanta.EXTRA_JUMLAH_AYAT
import com.example.projectequran.util.Constanta.EXTRA_NAMA_LATIN
import com.example.projectequran.util.Constanta.EXTRA_NOMOR_SURAT
import com.example.projectequran.util.Constanta.EXTRA_TEMPAT_TURUN
import com.example.projectequran.util.Constanta.TAB_TITLES
import com.example.projectequran.util.Constanta.currentAudio
import com.example.projectequran.util.Constanta.mediaPlayer
import com.example.projectequran.util.ViewStateCallback
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailActivity : AppCompatActivity(), ViewStateCallback<Surat> {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private var namaLatin: String? = ""
    private var nomorSurat: Int? = 0
    private var arti: String? = ""
    private var jumlahAyat: String? = ""
    private var tempatTurun: String? = ""
    private var deskripsi: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = null
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nomorSurat = intent.getIntExtra(EXTRA_NOMOR_SURAT, 0)

        getDetailSurat(nomorSurat)
        deskripsiDetail()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        namaLatin?.let { outState.putString(EXTRA_NAMA_LATIN, it) }
        nomorSurat?.let { outState.putInt(EXTRA_NOMOR_SURAT, it) }
        arti?.let { outState.putString(EXTRA_ARTI, it) }
        jumlahAyat?.let { outState.putString(EXTRA_JUMLAH_AYAT, it) }
        tempatTurun?.let { outState.putString(EXTRA_TEMPAT_TURUN, it) }
        deskripsi?.let { outState.putString(EXTRA_DESKRIPSI, it) }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        namaLatin = savedInstanceState.getString(EXTRA_NAMA_LATIN)
        nomorSurat = savedInstanceState.getInt(EXTRA_NOMOR_SURAT)
        arti = savedInstanceState.getString(EXTRA_ARTI)
        jumlahAyat = savedInstanceState.getString(EXTRA_JUMLAH_AYAT)
        tempatTurun = savedInstanceState.getString(EXTRA_TEMPAT_TURUN)
        deskripsi = savedInstanceState.getString(EXTRA_DESKRIPSI)
    }

    private fun deskripsiDetail() {
        binding.btnDetailBaca.setOnClickListener {
            startActivity(
                Intent(this, DeskripsiActivity::class.java)
                    .putExtra(EXTRA_NAMA_LATIN, namaLatin)
                    .putExtra(EXTRA_TEMPAT_TURUN, tempatTurun)
                    .putExtra(EXTRA_JUMLAH_AYAT, jumlahAyat)
                    .putExtra(EXTRA_ARTI, arti)
                    .putExtra(EXTRA_DESKRIPSI, deskripsi)
            )
        }
    }

    private fun getDetailSurat(nomorSurat: Int?) {
        CoroutineScope(Dispatchers.Main).launch {
            nomorSurat?.let { it ->
                viewModel.getSuratDetail(it)
                    .observe(this@DetailActivity) {
                        when (it) {
                            is Resource.Error -> onFailed(it.message)
                            is Resource.Loading -> onLoading()
                            is Resource.Success -> it.data?.let { it1 -> onSuccess(it1) }
                            else -> {}
                        }
                    }
            }
        }
    }

    private fun setTabLayout(nomorSurat: Int, namaLatin: String) {
        val sectionPagerAdapter = SectionTabAdapter(this, nomorSurat, namaLatin)

        binding.apply {
            container.adapter = sectionPagerAdapter
            TabLayoutMediator(tabs, container) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    override fun onSuccess(data: Surat) {
        namaLatin = data.namaLatin
        nomorSurat = data.nomor
        arti = data.arti
        jumlahAyat = data.jumlahAyat.toString()
        tempatTurun = data.tempatTurun
        deskripsi = data.deskripsi
        val enhancedDeskripsi = deskripsi?.replace("<i>", "")
        val enhancedDeskripsi2 = enhancedDeskripsi?.replace("</i>", "")

        binding.apply {
            tvDetailNamaSurat.apply {
                text = namaLatin
                setTextColor(Color.WHITE)
                foreground = ColorDrawable(Color.TRANSPARENT)
            }

            tvDetailDivider.apply {
                text = getString(R.string.divider)
                setTextColor(Color.WHITE)
                foreground = ColorDrawable(Color.TRANSPARENT)
            }

            tvDetailPlaceholderAyat.apply {
                text = getString(R.string.ayat)
                setTextColor(Color.WHITE)
                foreground = ColorDrawable(Color.TRANSPARENT)
            }

            tvDetailJumlahAyat.apply {
                text = jumlahAyat
                setTextColor(Color.WHITE)
                foreground = ColorDrawable(Color.TRANSPARENT)
            }
            tvDetailTempatTurun.apply {
                text = tempatTurun
                setTextColor(Color.WHITE)
                foreground = ColorDrawable(Color.TRANSPARENT)
            }

            tvDetailArtiSurat.apply {
                text = arti
                setTextColor(Color.WHITE)
                foreground = ColorDrawable(Color.TRANSPARENT)
            }

            tvDetailDeskripsi.apply {
                text = enhancedDeskripsi2
                setTextColor(Color.WHITE)
                foreground = ColorDrawable(Color.TRANSPARENT)
            }

            btnDetailPlay.apply {
                foreground = ColorDrawable(Color.TRANSPARENT)
            }

            btnDetailBaca.apply {
                foreground = ColorDrawable(Color.TRANSPARENT)
            }

            container.apply {
                setBackgroundColor(Color.WHITE)
            }
        }

        val audio = data.audio["04"]

        binding.btnDetailPlay.setOnClickListener {
            // TODO: 10/10/2021 - Play Audio
            if (currentAudio == audio) {
                if (mediaPlayer.isPlaying) {
                    Toast.makeText(binding.root.context, "Pause Audio...", Toast.LENGTH_SHORT)
                        .show()
                    mediaPlayer.pause()
                    binding.btnDetailPlay.setImageResource(R.drawable.play_button_light)
                } else {
                    Toast.makeText(binding.root.context, "Resume Audio...", Toast.LENGTH_SHORT)
                        .show()
                    mediaPlayer.start()
                    binding.btnDetailPlay.setImageResource(R.drawable.pause_button_light)
                }
            } else {
                currentAudio = audio
                mediaPlayer.stop()
                mediaPlayer.reset()
                Toast.makeText(binding.root.context, "Play Audio...", Toast.LENGTH_SHORT)
                    .show()
                audio?.let { it1 -> audioPlayer(it1) }
                binding.btnDetailPlay.setImageResource(R.drawable.pause_button_light)
            }
        }

        nomorSurat?.let { namaLatin?.let { it1 -> setTabLayout(it, it1) } }
    }

    private fun audioPlayer(path: String) {
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )

        mediaPlayer.setDataSource(path)
        mediaPlayer.prepare()
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.stop()
            mediaPlayer.reset()
            binding.btnDetailPlay.setImageResource(R.drawable.play_button_light)
        }
        mediaPlayer.start()
    }

    override fun onLoading() {
        binding.apply {
            tvDetailNamaSurat.apply {
                setTextColor(Color.rgb(221,221,221))
                foreground = ColorDrawable(Color.rgb(221,221,221))
            }

            tvDetailDivider.apply {
                setTextColor(Color.rgb(221,221,221))
                foreground = ColorDrawable(Color.rgb(221,221,221))
            }

            tvDetailPlaceholderAyat.apply {
                setTextColor(Color.rgb(221,221,221))
                foreground = ColorDrawable(Color.rgb(221,221,221))
            }

            tvDetailJumlahAyat.apply {
                setTextColor(Color.rgb(221,221,221))
                foreground = ColorDrawable(Color.rgb(221,221,221))
            }
            tvDetailTempatTurun.apply {
                setTextColor(Color.rgb(221,221,221))
                foreground = ColorDrawable(Color.rgb(221,221,221))
            }
            tvDetailArtiSurat.apply {
                setTextColor(Color.rgb(221,221,221))
                foreground = ColorDrawable(Color.rgb(221,221,221))
            }
            tvDetailDeskripsi.apply {
                setTextColor(Color.rgb(221,221,221))
                foreground = ColorDrawable(Color.rgb(221,221,221))
            }

            btnDetailPlay.apply {
                foreground = ColorDrawable(Color.rgb(221,221,221))
            }

            btnDetailBaca.apply {
                foreground = ColorDrawable(Color.rgb(221,221,221))
            }

            container.apply {
                setBackgroundColor(Color.rgb(221,221,221))
            }
        }
    }

    override fun onFailed(message: String?) {
        binding.container.visibility = View.INVISIBLE
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