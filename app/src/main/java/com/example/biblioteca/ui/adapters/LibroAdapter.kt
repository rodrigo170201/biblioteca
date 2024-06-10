package com.example.biblioteca.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.biblioteca.R
import com.example.biblioteca.models.Libro

/*class LibroAdapter(private var libros:List<Libro>):RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_libro, parent, false)
        return LibroViewHolder(view)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libro = libros[position]
        holder.bind(libro)
    }

    override fun getItemCount(): Int {
        return libros.size
    }

    fun actualizarLibros(nuevaLista: List<Libro>) {
        libros = nuevaLista
        notifyDataSetChanged()
    }

    class LibroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tituloTextView: TextView = itemView.findViewById(R.id.tituloTextView)
        private val autorTextView: TextView = itemView.findViewById(R.id.autorTextView)

        fun bind(libro: Libro) {
            tituloTextView.text = libro.titulo
            autorTextView.text = libro.autor
        }
    }
}*/