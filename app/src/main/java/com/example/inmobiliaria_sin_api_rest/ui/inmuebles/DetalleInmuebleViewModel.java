package com.example.inmobiliaria_sin_api_rest.ui.inmuebles;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmobiliaria_sin_api_rest.modelo.Inmueble;
import com.example.inmobiliaria_sin_api_rest.request.ApiClient;

public class DetalleInmuebleViewModel extends AndroidViewModel {

    private Context context;
    private ApiClient api;

    public DetalleInmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        api = ApiClient.getApi();
    }

    public void actualizarInmueble(Inmueble inmueble) {
        api.actualizarInmueble(inmueble);
    }
}
