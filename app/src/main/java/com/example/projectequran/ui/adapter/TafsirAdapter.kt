package com.example.projectequran.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectequran.databinding.ItemRowTafsirBinding
import com.example.projectequran.model.Tafsir
import com.example.projectequran.ui.tafsir.TafsirActivity
import com.example.projectequran.util.Constanta.EXTRA_NAMA_LATIN
import com.example.projectequran.util.Constanta.EXTRA_NOMOR_AYAT
import com.example.projectequran.util.Constanta.EXTRA_NOMOR_SURAT
import com.example.projectequran.util.Constanta.EXTRA_TAFSIR

class TafsirAdapter: RecyclerView.Adapter<TafsirAdapter.ViewHolder>() {

    private val listTafsir = ArrayList<Tafsir>()
    private var nomor = 0
    private var namaSurat = ""

    fun setNamaDanNomorSurat(namaSurat: String, nomorSurat: Int) {
        this.namaSurat = namaSurat
        this.nomor = nomorSurat
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAllData(data: List<Tafsir>) {
        listTafsir.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }
    inner class ViewHolder(private val binding: ItemRowTafsirBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tafsir: Tafsir) {
            binding.apply {
                nomorSurat.text = nomor.toString()
                nomorAyat.text = tafsir.nomorAyat.toString()
                tafsirSurat.text = tafsir.teksTafsir
            }

            binding.btnBaca.setOnClickListener {
                itemView.context.startActivity(
                    Intent(itemView.context, TafsirActivity::class.java)
                        .putExtra(EXTRA_NOMOR_SURAT, nomor)
                        .putExtra(EXTRA_TAFSIR, tafsir.teksTafsir)
                        .putExtra(EXTRA_NOMOR_AYAT, tafsir.nomorAyat)
                        .putExtra(EXTRA_NAMA_LATIN, namaSurat)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TafsirAdapter.ViewHolder {
        return ViewHolder(
            ItemRowTafsirBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listTafsir.size

    override fun onBindViewHolder(holder: TafsirAdapter.ViewHolder, position: Int) = with(holder) {
        bind(listTafsir[position])
    }
}