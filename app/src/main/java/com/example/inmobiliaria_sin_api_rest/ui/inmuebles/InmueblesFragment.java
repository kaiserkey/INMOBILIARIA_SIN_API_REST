package com.example.inmobiliaria_sin_api_rest.ui.inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliaria_sin_api_rest.databinding.FragmentInmueblesBinding;
import com.example.inmobiliaria_sin_api_rest.modelo.Inmueble;

import java.util.ArrayList;

public class InmueblesFragment extends Fragment {

    private InmueblesViewModel mv;
    private FragmentInmueblesBinding binding;
    public static InmueblesFragment newInstance() {
        return new InmueblesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        mv = new ViewModelProvider(this).get(InmueblesViewModel.class);
        View root = binding.getRoot();

        mv.obtenerPropiedades();

        mv.getInmuebles().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {

                RecyclerView rv = binding.rvLista;
                GridLayoutManager grilla = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(grilla);
                InmueblesAdapter adapter = new InmueblesAdapter(getContext(), inmuebles, getLayoutInflater());
                rv.setAdapter(adapter);

            }
        });



        return root;
    }

}
