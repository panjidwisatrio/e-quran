package com.example.projectequran.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectequran.databinding.ItemRowSuratBinding
import com.example.projectequran.model.Surat
import com.example.projectequran.ui.detail.DetailActivity
import com.example.projectequran.util.Constanta.EXTRA_NOMOR

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
                number.text = surat.nomor
                namaSurat.text = surat.namaLatin
                tempatTurun.text = surat.tempatTurun
                artiSurat.text = surat.arti
                namaSuratArab.text = surat.nama
            }

            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(itemView.context, DetailActivity::class.java)
                        .putExtra(EXTRA_NOMOR, surat.nomor)
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