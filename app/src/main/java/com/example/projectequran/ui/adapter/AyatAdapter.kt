package com.example.projectequran.ui.adapter

import android.annotation.SuppressLint
import android.media.AudioAttributes
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projectequran.R
import com.example.projectequran.databinding.ItemRowAyatBinding
import com.example.projectequran.model.Ayat
import com.example.projectequran.util.Constanta.currentAudio
import com.example.projectequran.util.Constanta.mediaPlayer

class AyatAdapter : RecyclerView.Adapter<AyatAdapter.ViewHolder>() {
    private val listAyat = ArrayList<Ayat>()
    private var nomor = 0

    fun setNomorSurat(nomorSurat: Int) {
        this.nomor = nomorSurat
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAllData(data: List<Ayat>) {
        listAyat.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemRowAyatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ayat: Ayat) {
            binding.apply {
                nomorSurat.text = nomor.toString()
                nomorAyat.text = ayat.nomorAyat.toString()
                teksArab.text = ayat.teksArab
                teksLatin.text = ayat.teksLatin
                artiSurat.text = ayat.teksIndonesia
            }

            val audio = ayat.audio["04"]

            binding.btnPlay.setOnClickListener {
                // TODO: 10/10/2021 - Play Audio
                if (currentAudio == audio) {
                    if (mediaPlayer.isPlaying) {
                        Toast.makeText(binding.root.context, "Pause Audio...", Toast.LENGTH_SHORT)
                            .show()
                        mediaPlayer.pause()
                        binding.btnPlay.setImageResource(R.drawable.play_button_dark)
                    } else {
                        Toast.makeText(binding.root.context, "Resume Audio...", Toast.LENGTH_SHORT)
                            .show()
                        mediaPlayer.start()
                        binding.btnPlay.setImageResource(R.drawable.pause_button_dark)
                    }
                } else {
                    currentAudio = audio
                    mediaPlayer.stop()
                    mediaPlayer.reset()
                    Toast.makeText(binding.root.context, "Play Audio...", Toast.LENGTH_SHORT)
                        .show()
                    audio?.let { it1 -> audioPlayer(it1, binding) }
                    binding.btnPlay.setImageResource(R.drawable.pause_button_dark)
                }
            }
        }
    }

    private fun audioPlayer(path: String, binding: ItemRowAyatBinding) {
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
            binding.btnPlay.setImageResource(R.drawable.play_button_dark)
        }
        mediaPlayer.start()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRowAyatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listAyat.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder) {
        bind(listAyat[position])
    }
}