package com.example.projectequran.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectequran.databinding.ItemRowSuratBinding
import com.example.projectequran.model.Surat
import com.example.projectequran.ui.detail.DetailActivity
import com.example.projectequran.util.Constanta.EXTRA_ARTI
import com.example.projectequran.util.Constanta.EXTRA_AUDIO
import com.example.projectequran.util.Constanta.EXTRA_DESKRIPSI
import com.example.projectequran.util.Constanta.EXTRA_JUMLAH_AYAT
import com.example.projectequran.util.Constanta.EXTRA_NAMA_LATIN
import com.example.projectequran.util.Constanta.EXTRA_NOMOR
import com.example.projectequran.util.Constanta.EXTRA_TEMPAT_TURUN

class SuratAdapter: RecyclerView.Adapter<SuratAdapter.ViewHolder>() {

    private val listSurat = ArrayList<Surat>()

    @SuppressLint("NotifyDataSetChanged")
    fun setAllData(data: List<Surat>) {
        listSurat.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemRowSuratBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(surat: Surat) {
            binding.apply {
                number.text = surat.nomor.toString()
                namaSurat.text = surat.namaLatin
                tempatTurun.text = surat.tempatTurun
                artiSurat.text = surat.arti
                namaSuratArab.text = surat.nama
            }

            val audio = surat.audio["01"]

            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(itemView.context, DetailActivity::class.java)
                        .putExtra(EXTRA_NOMOR, surat.nomor)
                        .putExtra(EXTRA_NAMA_LATIN, surat.namaLatin)
                        .putExtra(EXTRA_JUMLAH_AYAT, surat.jumlahAyat)
                        .putExtra(EXTRA_TEMPAT_TURUN, surat.tempatTurun)
                        .putExtra(EXTRA_ARTI, surat.arti)
                        .putExtra(EXTRA_DESKRIPSI, surat.deskripsi)
                        .putExtra(EXTRA_AUDIO, audio)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRowSuratBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = listSurat.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder) {
        bind(listSurat[position])
    }
}