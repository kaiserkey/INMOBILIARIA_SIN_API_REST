package com.example.inmobiliaria_sin_api_rest.ui.contratos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmobiliaria_sin_api_rest.request.ApiClient;

public class DetallePagosViewModel extends AndroidViewModel {

    private Context context;
    private ApiClient api;

    public DetallePagosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        api = ApiClient.getApi();
    }

}
