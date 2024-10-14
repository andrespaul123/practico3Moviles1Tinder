package com.example.practico3movil1.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.practico3movil1.R
import com.example.practico3movil1.models.PersonaModel
import com.example.practico3movil1.ui.viewmodels.ViewmodelTinder

class Tinder : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var lblName: TextView
    private lateinit var br1: ProgressBar
    private lateinit var br2: ProgressBar
    private lateinit var br3: ProgressBar
    private lateinit var btnAddLike: ImageView
    private lateinit var btnDeslike: ImageView
    private lateinit var btnlike: ImageView

    private val viewModel: ViewmodelTinder by viewModels()


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tinder2, container, false)

        imageView = view.findViewById(R.id.imageView2)
        lblName = view.findViewById(R.id.lblName)
        br1 = view.findViewById(R.id.brr1)
        br2 = view.findViewById(R.id.brr2)
        br3 = view.findViewById(R.id.brr3)
        btnAddLike = view.findViewById(R.id.btnAddLike)
        btnDeslike = view.findViewById(R.id.btnDeslike)
        btnlike = view.findViewById(R.id.btnlike)

        viewModel.personas.observe(viewLifecycleOwner, Observer { personas ->
            Log.d("TinderFragment", "Observer activado. Número de personas: ${personas.size}")
            if (personas.isNotEmpty()) {
                mostrarPersona(viewModel.getCurrentPersona())
            }
        })

        imageView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    val screenWidth = view.width
                    val touchX = event.x

                    val photo = if (touchX < screenWidth / 2) {
                        viewModel.mostrarFotoAnterior()
                    } else {
                        viewModel.mostrarFotoSiguiente()
                    }
                    photo?.let { imageView.setImageResource(it) }
                    mostrarPersona(viewModel.getCurrentPersona())
                }
            }
            true
        }

        setupEventListeners()

        return view
    }

    private fun setupEventListeners() {
        btnAddLike.setOnClickListener {
            viewModel.darLike()
            mostrarPersona(viewModel.getCurrentPersona())
        }

        btnDeslike.setOnClickListener {
            viewModel.eliminarPersona()
            mostrarPersona(viewModel.getCurrentPersona())
        }

        btnlike.setOnClickListener {
            findNavController().navigate(R.id.action_tinder_to_likeFragment)
        }
    }

    private fun mostrarPersona(persona: PersonaModel?) {
        persona?.let {
            lblName.text = it.nombre
            if (it.fotos.isNotEmpty()) {
                imageView.setImageResource(it.fotos[viewModel.getCurrentPhotoIndex()])
            }
            actualizarProgressBars(it.fotos.size)
        }
    }

    private fun actualizarProgressBars(numeroFotos: Int) {
        br1.visibility = if (numeroFotos > 0) View.VISIBLE else View.GONE
        br2.visibility = if (numeroFotos > 1) View.VISIBLE else View.GONE
        br3.visibility = if (numeroFotos > 2) View.VISIBLE else View.GONE

        br1.isIndeterminate = false
        br2.isIndeterminate = false
        br3.isIndeterminate = false

        br1.progress = if (viewModel.getCurrentPhotoIndex() >= 0) 100 else 0
        br2.progress = if (viewModel.getCurrentPhotoIndex() >= 1) 100 else 0
        br3.progress = if (viewModel.getCurrentPhotoIndex() >= 2) 100 else 0
    }
}
