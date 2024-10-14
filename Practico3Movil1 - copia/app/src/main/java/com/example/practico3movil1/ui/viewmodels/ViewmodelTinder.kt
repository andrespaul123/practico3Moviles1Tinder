package com.example.practico3movil1.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico3movil1.R
import com.example.practico3movil1.models.BaseFake
import com.example.practico3movil1.models.PersonaModel

class ViewmodelTinder : ViewModel() {

    private val _personas: MutableLiveData<List<PersonaModel>> = MutableLiveData()
    val personas: LiveData<List<PersonaModel>> get() = _personas

    private var currentPersonaIndex: Int = 0
    private var currentPhotoIndex: Int = 0

    init {
        _personas.value = listOf(
            PersonaModel("Juancito Pinto", listOf(R.drawable.foto1, R.drawable.foto2, R.drawable.foto3)),
            PersonaModel("Perico de los Palotes", listOf(R.drawable.foto3, R.drawable.foto2, R.drawable.foto1)),
            PersonaModel("thor", listOf(R.drawable.thor1, R.drawable.thor2, R.drawable.thor3)),
            PersonaModel("Capitan America", listOf(R.drawable.capitan1, R.drawable.capitan2, R.drawable.capitan3)),
            PersonaModel("Ojo de halcón", listOf(R.drawable.ojo1, R.drawable.ojo2, R.drawable.ojo3)),
            PersonaModel("Doctor Strange", listOf(R.drawable.doctor1, R.drawable.doctor2, R.drawable.doctor3)),
            PersonaModel("Falcon", listOf(R.drawable.falco1, R.drawable.falco2, R.drawable.falco3)),
            PersonaModel("Scarlett", listOf(R.drawable.scarlet1, R.drawable.scarlet2, R.drawable.scarlet3)),
            PersonaModel("Wanda", listOf(R.drawable.bruja1, R.drawable.bruja2, R.drawable.bruja3))

        )
    }
    fun getCurrentPhotoIndex(): Int {
        return currentPhotoIndex
    }

    fun getCurrentPersona(): PersonaModel? {
        return _personas.value?.getOrNull(currentPersonaIndex)
    }

    fun mostrarFotoAnterior(): Int? {
        if (currentPhotoIndex > 0) {
            currentPhotoIndex--
        }
        return getCurrentPersona()?.fotos?.get(currentPhotoIndex)
    }

    fun mostrarFotoSiguiente(): Int? {
        val totalFotos = getCurrentPersona()?.fotos?.size ?: 0
        if (currentPhotoIndex < totalFotos - 1) {
            currentPhotoIndex++
        //} else {
         //   avanzarALaSiguientePersona()
        }
        return getCurrentPersona()?.fotos?.get(currentPhotoIndex)
    }

    fun darLike() {
        val persona = getCurrentPersona()
        if (persona != null) {
            BaseFake.agregarPersonaConLike(persona)
            Log.d("ViewmodelTinder", "Persona guardada en BaseFake: ${persona.nombre}")
            avanzarALaSiguientePersona()
        }
    }

    fun eliminarPersona() {
        avanzarALaSiguientePersona()
    }


    fun avanzarALaSiguientePersona() {
        if (currentPersonaIndex < (_personas.value?.size ?: 0) - 1) {
            currentPersonaIndex++
            currentPhotoIndex = 0
        }
    }

}
