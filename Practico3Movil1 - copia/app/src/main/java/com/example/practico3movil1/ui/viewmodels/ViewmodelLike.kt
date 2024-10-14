package com.example.practico3movil1.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico3movil1.models.BaseFake
import com.example.practico3movil1.models.PersonaModel

class ViewmodelLike : ViewModel() {

    private val _personasConLike: MutableLiveData<List<PersonaModel>> = MutableLiveData()

    init {
        _personasConLike.value = BaseFake.obtenerPersonasConLike()
    }

    fun obtenerPersonasConLikeObserver(): LiveData<List<PersonaModel>> {
        return _personasConLike
    }
}
