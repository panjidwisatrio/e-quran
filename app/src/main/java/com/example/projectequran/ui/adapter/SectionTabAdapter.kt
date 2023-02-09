package com.example.projectequran.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.projectequran.ui.ayat.AyatFragment
import com.example.projectequran.ui.tafsir.TafsirFragment
import com.example.projectequran.util.Constanta.TAB_TITLES

class SectionTabAdapter(activity: AppCompatActivity, private val nomorSurat: Int) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = TAB_TITLES.size

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = AyatFragment.getInstance(nomorSurat)
            1 -> fragment = TafsirFragment.getInstance(nomorSurat)
        }
        return fragment as Fragment
    }
}