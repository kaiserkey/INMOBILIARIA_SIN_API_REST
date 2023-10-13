package com.example.inmobiliaria_sin_api_rest.ui.inquilinos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.inmobiliaria_sin_api_rest.databinding.FragmentDetalleInquilinoBinding;
import com.example.inmobiliaria_sin_api_rest.modelo.Inquilino;

public class DetalleInquilinoFragment extends Fragment {

    private FragmentDetalleInquilinoBinding binding;
    private Inquilino inquilino;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetalleInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle bundle = getArguments();
        inquilino = (Inquilino) bundle.getSerializable("inquilino");


        binding.tvCodigo.setText(String.valueOf(inquilino.getIdInquilino()));
        binding.tvDni.setText(String.valueOf(inquilino.getDNI()));
        binding.tvNombre.setText(inquilino.getNombre());
        binding.tvApellido.setText(inquilino.getApellido());
        binding.tvMail.setText(inquilino.getEmail());
        binding.tvTelefono.setText(inquilino.getTelefono());
        binding.tvTelefonoGarante.setText(inquilino.getTelefonoGarante());
        binding.tvGarante.setText(inquilino.getNombreGarante());


        return root;
    }
}