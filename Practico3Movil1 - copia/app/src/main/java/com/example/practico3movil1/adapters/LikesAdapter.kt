package com.example.practico3movil1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practico3movil1.R
import com.example.practico3movil1.models.PersonaModel

class LikesAdapter(
    private val personas: List<PersonaModel>
) : RecyclerView.Adapter<LikesAdapter.LikesViewHolder>() {

    class LikesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProfile: ImageView = itemView.findViewById(R.id.imgProfile)
        val txtNameProfile: TextView = itemView.findViewById(R.id.txtNameProfile)
       // val txtLikesProfile: TextView = itemView.findViewById(R.id.txtLikesProfile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listalike, parent, false)
        return LikesViewHolder(view)
    }

    override fun onBindViewHolder(holder: LikesViewHolder, position: Int) {
        val persona = personas[position]
        holder.imgProfile.setImageResource(persona.fotos[0])
        holder.txtNameProfile.text = persona.nombre
        //holder.txtLikesProfile.text = if (persona.isLiked) "Likes: 1" else "Likes: 0"
    }

    override fun getItemCount(): Int {
        return personas.size
    }
}
