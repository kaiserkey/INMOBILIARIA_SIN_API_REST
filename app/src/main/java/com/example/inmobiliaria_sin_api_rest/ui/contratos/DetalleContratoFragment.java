package com.example.inmobiliaria_sin_api_rest.ui.contratos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.inmobiliaria_sin_api_rest.R;
import com.example.inmobiliaria_sin_api_rest.databinding.FragmentDetalleContratoBinding;
import com.example.inmobiliaria_sin_api_rest.modelo.Contrato;
import com.example.inmobiliaria_sin_api_rest.modelo.Inmueble;
import com.example.inmobiliaria_sin_api_rest.modelo.Inquilino;
import com.example.inmobiliaria_sin_api_rest.modelo.Pago;

import java.util.ArrayList;

public class DetalleContratoFragment extends Fragment {

    private FragmentDetalleContratoBinding binding;
    private Contrato contrato;
    private DetalleContratoViewModel mv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetalleContratoBinding.inflate(getLayoutInflater());
        mv = new ViewModelProvider(this).get(DetalleContratoViewModel.class);
        View root = binding.getRoot();


        Bundle bundle = getArguments();
        contrato = (Contrato) bundle.getSerializable("contratoDelInmueble");

        Inquilino inquilino = contrato.getInquilino();
        Inmueble inmueble = contrato.getInmueble();

        binding.tvCodigo.setText(String.valueOf(contrato.getIdContrato()));
        binding.tvMontoMensual.setText(String.valueOf(contrato.getMontoAlquiler()));
        binding.tvFechaInicio.setText(contrato.getFechaInicio());
        binding.tvFechaFinalizacion.setText(contrato.getFechaFin());
        binding.tvInquilino.setText(inquilino.getNombre());
        binding.tvInmueble.setText(inmueble.getDireccion());

        binding.btnPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.obtenerPagos(contrato);

                mv.getPagosMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pago>>() {
                    @Override
                    public void onChanged(ArrayList<Pago> pagos) {
                        NavController navController = Navigation.findNavController(v);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("pagosAsociados", pagos);
                        navController.navigate(R.id.nav_detallePagosFragment, bundle);
                    }
                });
            }
        });



        return root;
    }


}
