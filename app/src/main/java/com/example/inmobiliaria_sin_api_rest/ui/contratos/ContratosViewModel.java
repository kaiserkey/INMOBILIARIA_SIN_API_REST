package com.example.inmobiliaria_sin_api_rest.ui.contratos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliaria_sin_api_rest.modelo.Inmueble;
import com.example.inmobiliaria_sin_api_rest.request.ApiClient;

import java.util.ArrayList;

public class ContratosViewModel extends AndroidViewModel {

    private ApiClient api;
    private Context context;
    private MutableLiveData<ArrayList<Inmueble>> proiedadesAlquiladasMutable;

    public ContratosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        api = ApiClient.getApi();
    }

    public LiveData<ArrayList<Inmueble>> getProiedadesAlquiladasMutable() {
        if(proiedadesAlquiladasMutable == null){
            proiedadesAlquiladasMutable = new MutableLiveData<>();
        }
        return proiedadesAlquiladasMutable;
    }

    public void propiedadesAlquiladas(){
        if(proiedadesAlquiladasMutable == null){
            proiedadesAlquiladasMutable = new MutableLiveData<>();
        }
        ArrayList<Inmueble> inmuebles = api.obtenerPropiedadesAlquiladas();
        proiedadesAlquiladasMutable.postValue(inmuebles);
    }

}
