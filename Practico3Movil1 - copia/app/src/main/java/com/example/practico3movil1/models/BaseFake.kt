package com.example.practico3movil1.models

object BaseFake {
    private val personasConLike: MutableList<PersonaModel> = mutableListOf()

    fun agregarPersonaConLike(persona: PersonaModel) {
        if(!personasConLike.contains(persona)) {
            personasConLike.add(persona)
        }


    }

    fun obtenerPersonasConLike(): List<PersonaModel> {
        return personasConLike
    }
}

