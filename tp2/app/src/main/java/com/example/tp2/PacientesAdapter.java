package com.example.tp2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PacientesAdapter extends RecyclerView.Adapter<PacientesAdapter.ViewHolder> {

    private final ArrayList<String> listaPacientes;

    public PacientesAdapter(ArrayList<String> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflamos el layout para cada ítem de la lista
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Establecemos el texto del paciente en cada ítem
        holder.textView.setText(listaPacientes.get(position));
    }

    @Override
    public int getItemCount() {
        // Retornamos el número de pacientes
        return listaPacientes.size();
    }

    // ViewHolder para almacenar las vistas de cada ítem
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
