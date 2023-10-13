package com.example.inmobiliaria_sin_api_rest.ui.inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliaria_sin_api_rest.databinding.FragmentDetalleInmuebleBinding;
import com.example.inmobiliaria_sin_api_rest.modelo.Inmueble;
import com.squareup.picasso.Picasso;

public class DetalleInmuebleFragment extends Fragment {

    private DetalleInmuebleViewModel mv;
    private FragmentDetalleInmuebleBinding binding;
    private Inmueble inmueble;

    public static DetalleInmuebleFragment newInstance() {
        return new DetalleInmuebleFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetalleInmuebleBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(DetalleInmuebleViewModel.class);
        View root = binding.getRoot();

        Bundle bundle = getArguments();
        inmueble = (Inmueble) bundle.getSerializable("inmueble");

        binding.tvCodigo.setText(String.valueOf(inmueble.getIdInmueble()));
        binding.tvAmbientes.setText(String.valueOf(inmueble.getAmbientes()));
        binding.tvDireccion.setText(inmueble.getDireccion());
        binding.tvPrecio.setText(String.valueOf(inmueble.getPrecio()));
        binding.tvUso.setText(inmueble.getUso());
        binding.tvTipo.setText(inmueble.getTipo());
        Picasso.get().load(inmueble.getImagen()).into(binding.imageView2);
        binding.cbEstado.setChecked(inmueble.isEstado());
        binding.cbEstado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inmueble.setEstado(binding.cbEstado.isChecked());
                mv.actualizarInmueble(inmueble);
            }
        });


        return root;
    }


}
