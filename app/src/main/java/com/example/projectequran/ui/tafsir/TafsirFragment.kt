package com.example.projectequran.ui.tafsir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.projectequran.databinding.FragmentTafsirBinding
import com.example.projectequran.model.Tafsir
import com.example.projectequran.ui.adapter.TafsirAdapter
import com.example.projectequran.util.ViewStateCallback

class TafsirFragment : Fragment(), ViewStateCallback<List<Tafsir>> {

    private lateinit var binding: FragmentTafsirBinding
    private lateinit var tafsirAdapter: TafsirAdapter
    private val viewModel: TafsirViewModel by viewModels()
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
        binding = FragmentTafsirBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tafsirAdapter = TafsirAdapter()
    }

    override fun onSuccess(data: List<Tafsir>) {
        TODO("Not yet implemented")
    }

    override fun onLoading() {
        TODO("Not yet implemented")
    }

    override fun onFailed(message: String?) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val KEY_BUNDLE = "key_bundle"

        fun getInstance(nomorSurat: Int): Fragment {
            return TafsirFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_BUNDLE, nomorSurat)
                }
            }
        }
    }
}