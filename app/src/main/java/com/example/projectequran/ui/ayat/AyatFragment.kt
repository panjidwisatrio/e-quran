package com.example.projectequran.ui.ayat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectequran.data.Resource
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
        binding.rvAyat.apply {
            visibility = View.INVISIBLE
            adapter = ayatAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getAyat(nomorSurat).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> onFailed(it.message)
                is Resource.Loading -> onLoading()
                is Resource.Success -> it.data?.let { it1 -> onSuccess(it1) }
            }
        }
    }

    override fun onSuccess(data: List<Ayat>) {
        ayatAdapter.setAllData(data)
        ayatAdapter.setNomorSurat(nomorSurat)
        binding.apply {
            rvAyat.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
            ivError.visibility = View.INVISIBLE
            tvError.visibility = View.INVISIBLE
        }
    }

    override fun onLoading() {
        binding.apply {
            rvAyat.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
            ivError.visibility = View.INVISIBLE
            tvError.visibility = View.INVISIBLE
        }
    }

    override fun onFailed(message: String?) {
        binding.apply {
            rvAyat.visibility = View.INVISIBLE
            progressBar.visibility = View.INVISIBLE
            ivError.visibility = View.VISIBLE
            if (message != null) {
                tvError.text = message
                tvError.visibility = View.VISIBLE
            } else {
                tvError.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
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
}