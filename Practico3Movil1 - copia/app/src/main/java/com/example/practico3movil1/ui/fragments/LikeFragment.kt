package com.example.practico3movil1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practico3movil1.R
import com.example.practico3movil1.adapters.LikesAdapter
import com.example.practico3movil1.ui.viewmodels.ViewmodelLike

class LikeFragment : Fragment() {

    private val viewModel: ViewmodelLike by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LikesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_like, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.obtenerPersonasConLikeObserver().observe(viewLifecycleOwner) { personas ->
            println("like" + personas)
            adapter = LikesAdapter(personas)
            recyclerView.adapter = adapter
        }

        return view
    }
}
