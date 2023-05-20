package com.example.projectequran.ui.tafsir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectequran.data.Resource
import com.example.projectequran.databinding.FragmentTafsirBinding
import com.example.projectequran.model.Tafsir
import com.example.projectequran.ui.adapter.TafsirAdapter
import com.example.projectequran.util.ViewStateCallback

class TafsirFragment : Fragment(), ViewStateCallback<List<Tafsir>> {

    private lateinit var binding: FragmentTafsirBinding
    private lateinit var tafsirAdapter: TafsirAdapter
    private val viewModel: TafsirViewModel by viewModels()
    private var nomorSurat: Int = 0
    private var namaSurat: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nomorSurat = it.getInt(KEY_BUNDLE)
            namaSurat = it.getString(KEY_NAMA_SURAT)
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
        binding.rvTafsir.apply {
            visibility = View.INVISIBLE
            adapter = tafsirAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getTafsir(nomorSurat).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> onFailed(it.message)
                is Resource.Loading -> onLoading()
                is Resource.Success -> it.data?.let { it1 -> onSuccess(it1) }
            }
        }
    }

    override fun onSuccess(data: List<Tafsir>) {
        tafsirAdapter.setAllData(data)
        tafsirAdapter.setNamaDanNomorSurat(namaSurat.toString(), nomorSurat)
        binding.apply {
            rvTafsir.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
            ivError.visibility = View.INVISIBLE
            tvError.visibility = View.INVISIBLE

        }
    }

    override fun onLoading() {
        binding.apply {
            rvTafsir.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
            ivError.visibility = View.INVISIBLE
            tvError.visibility = View.INVISIBLE
        }
    }

    override fun onFailed(message: String?) {
        binding.apply {
            rvTafsir.visibility = View.INVISIBLE
            progressBar.visibility = View.INVISIBLE
            ivError.visibility = View.VISIBLE
            tvError.visibility = View.VISIBLE
        }
    }

    companion object {
        private const val KEY_BUNDLE = "key_bundle"
        private const val KEY_NAMA_SURAT = "key_nama_surat"

        fun getInstance(nomorSurat: Int, namaSurat:String): Fragment {
            return TafsirFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_BUNDLE, nomorSurat)
                    putString(KEY_NAMA_SURAT, namaSurat)
                }
            }
        }
    }
}