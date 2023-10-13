package com.example.inmobiliaria_sin_api_rest.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliaria_sin_api_rest.modelo.Propietario;
import com.example.inmobiliaria_sin_api_rest.request.ApiClient;

public class PerfilViewModel extends AndroidViewModel {

    private Context context;
    private ApiClient api;
    private MutableLiveData<Propietario> dataPropietarioMutable;
    private MutableLiveData<String> valorBotonMutable;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        api = ApiClient.getApi();

    }

    public LiveData<String> getValorBotonMutable() {
        if (valorBotonMutable == null) {
            valorBotonMutable = new MutableLiveData<>();
        }
        return valorBotonMutable;
    }

    public LiveData<Propietario> getDataPropietarioMutable() {
        if (dataPropietarioMutable == null) {
            dataPropietarioMutable = new MutableLiveData<>();
        }
        return dataPropietarioMutable;
    }

    public void cargarPropietarioLogueado() {
        if (dataPropietarioMutable == null) {
            dataPropietarioMutable = new MutableLiveData<>();
        }
        dataPropietarioMutable.setValue(api.obtenerUsuarioActual());
    }

    ;


    public void cambiarTextoBoton(String estadoBtn, String dni, String nombre, String apellido, String email, String clave, String telefono) {
        if (valorBotonMutable == null) {
            valorBotonMutable = new MutableLiveData<>();
        }

        try {
            if (estadoBtn.equals("EDITAR")) {
                valorBotonMutable.setValue("GUARDAR");
            } else {
                if (dni == null || nombre == null || apellido == null || email == null || clave == null || telefono == null || dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || clave.isEmpty() || telefono.isEmpty()) {
                    throw new IllegalArgumentException("Completa todos los campos");
                }

                long dniLong = Long.parseLong(dni);
                if (dniLong <= 0) {
                    throw new IllegalArgumentException("Ingresa un DNI válido");
                }

                Propietario propietario = dataPropietarioMutable.getValue();
                propietario.setDni(dniLong);
                propietario.setNombre(nombre);
                propietario.setApellido(apellido);
                propietario.setEmail(email);
                propietario.setContraseña(clave);
                propietario.setTelefono(telefono);

                api.actualizarPerfil(propietario);
                valorBotonMutable.setValue("EDITAR");
            }
        } catch (NumberFormatException e) {
            Toast.makeText(context, "Ingresa un DNI válido", Toast.LENGTH_SHORT).show();
        } catch (IllegalArgumentException e) {
            Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show();
        }
    }


}
