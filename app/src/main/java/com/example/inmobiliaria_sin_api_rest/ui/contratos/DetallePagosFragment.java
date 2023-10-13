package com.example.inmobiliaria_sin_api_rest.ui.contratos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliaria_sin_api_rest.databinding.FragmentDetallePagosBinding;
import com.example.inmobiliaria_sin_api_rest.modelo.Pago;

import java.util.ArrayList;

public class DetallePagosFragment extends Fragment {

    private FragmentDetallePagosBinding binding;
    private ArrayList<Pago> pagos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetallePagosBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        Bundle bundle = getArguments();
        pagos = (ArrayList<Pago>) bundle.getSerializable("pagosAsociados");


        RecyclerView rv = binding.rvLista;
        GridLayoutManager grilla = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        rv.setLayoutManager(grilla);
        PagosAdapter adapter = new PagosAdapter(getContext(), pagos, getLayoutInflater());
        rv.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        return root;
    }
}