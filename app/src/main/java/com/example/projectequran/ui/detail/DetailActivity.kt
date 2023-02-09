package com.example.projectequran.ui.detail

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.projectequran.R
import com.example.projectequran.databinding.ActivityDetailBinding
import com.example.projectequran.ui.adapter.AyatAdapter
import com.example.projectequran.ui.adapter.SectionTabAdapter
import com.example.projectequran.util.Constanta.EXTRA_ARTI
import com.example.projectequran.util.Constanta.EXTRA_AUDIO
import com.example.projectequran.util.Constanta.EXTRA_DESKRIPSI
import com.example.projectequran.util.Constanta.EXTRA_JUMLAH_AYAT
import com.example.projectequran.util.Constanta.EXTRA_NAMA_LATIN
import com.example.projectequran.util.Constanta.EXTRA_NOMOR
import com.example.projectequran.util.Constanta.EXTRA_TEMPAT_TURUN
import com.example.projectequran.util.Constanta.TAB_TITLES
import com.google.android.material.tabs.TabLayoutMediator


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel by viewModels<DetailViewModel>()
    private val mediaPlayer = MediaPlayer()
    private lateinit var ayatAdapter: AyatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = null
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setDetailSurat()
        setTabLayout()
    }

    private fun setDetailSurat() {
        val namaLatin = intent.getStringExtra(EXTRA_NAMA_LATIN)
        val jumlahAyat = intent.getIntExtra(EXTRA_JUMLAH_AYAT, 0)
        val tempatTurun = intent.getStringExtra(EXTRA_TEMPAT_TURUN)
        val arti = intent.getStringExtra(EXTRA_ARTI)
        val deskripsi = intent.getStringExtra(EXTRA_DESKRIPSI)
        val audio = intent.getStringExtra(EXTRA_AUDIO)

        binding.apply {
            tvDetailNamaSurat.text = namaLatin
            tvDetailJumlahAyat.text = jumlahAyat.toString()
            tvDetailTempatTurun.text = tempatTurun
            tvDetailArtiSurat.text = arti
            tvDetailDeskripsi.text = deskripsi
        }

        binding.btnDetailPlay.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.reset()
                binding.btnDetailPlay.setImageResource(R.drawable.play_button_light)
            } else {
                audio?.let { it1 -> audioPlayer(it1) }
                binding.btnDetailPlay.setImageResource(R.drawable.pause_button_light)
            }
        }
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

    private fun setTabLayout() {
        val nomorSurat = intent.getIntExtra(EXTRA_NOMOR, 0)
        val sectionPagerAdapter = SectionTabAdapter(this, nomorSurat)

        binding.apply {
            container.adapter = sectionPagerAdapter
            TabLayoutMediator(tabs, container) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }
}