package com.example.inmobiliaria_sin_api_rest;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmobiliaria_sin_api_rest.modelo.Propietario;
import com.example.inmobiliaria_sin_api_rest.request.ApiClient;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    public static ApiClient api;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        api = ApiClient.getApi();

    }

    public void confirmarLogin(String mail, String clave) {
        Propietario propietario = api.login(mail, clave);
        if (propietario != null) {
            Intent intent = new Intent(getApplication(), MenuActivity.class);
            intent.putExtra("nombre", propietario.getNombre() + " " + propietario.getApellido());
            intent.putExtra("email", propietario.getEmail());
            intent.putExtra("avatar", propietario.getAvatar());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getApplication().startActivity(intent);
        } else {
            Toast.makeText(getApplication(), "Credenciales invalidas", Toast.LENGTH_SHORT).show();
        }
    }



}
