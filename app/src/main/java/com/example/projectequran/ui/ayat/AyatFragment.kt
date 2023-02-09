package com.example.projectequran.ui.ayat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.projectequran.databinding.FragmentAyatBinding
import com.example.projectequran.model.Ayat
import com.example.projectequran.ui.adapter.AyatAdapter
import com.example.projectequran.util.ViewStateCallback

class AyatFragment : Fragment(), ViewStateCallback<List<Ayat>> {

    private lateinit var binding: FragmentAyatBinding
    private lateinit var ayatAdapter: AyatAdapter
    private val viewModel: AyatViewModel by viewModels()
    private var nomorSurat: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nomorSurat = it.getInt(KEY_BUNDLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAyatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ayatAdapter = AyatAdapter()
    }

    companion object {
        private const val KEY_BUNDLE = "key_bundle"

        fun getInstance(nomorSurat: Int): Fragment {
            return AyatFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_BUNDLE, nomorSurat)
                }
            }
        }
    }

    override fun onSuccess(data: List<Ayat>) {
        TODO("Not yet implemented")
    }

    override fun onLoading() {
        TODO("Not yet implemented")
    }

    override fun onFailed(message: String?) {
        TODO("Not yet implemented")
    }
}