package com.example.inmobiliaria_sin_api_rest.request;

import com.example.inmobiliaria_sin_api_rest.R;
import com.example.inmobiliaria_sin_api_rest.modelo.Contrato;
import com.example.inmobiliaria_sin_api_rest.modelo.Inmueble;
import com.example.inmobiliaria_sin_api_rest.modelo.Inquilino;
import com.example.inmobiliaria_sin_api_rest.modelo.Pago;
import com.example.inmobiliaria_sin_api_rest.modelo.Propietario;

import java.util.ArrayList;

public class ApiClient {
    private ArrayList<Propietario> propietarios = new ArrayList<>();
    private ArrayList<Inquilino> inquilinos = new ArrayList<>();
    private ArrayList<Inmueble> inmuebles = new ArrayList<>();
    private ArrayList<Contrato> contratos = new ArrayList<>();
    private ArrayList<Pago> pagos = new ArrayList<>();
    private static Propietario usuarioActual = null;
    private static ApiClient api = null;

    private ApiClient() {
        //Nos conectamos a nuestra "Base de Datos"
        cargaDatos();
    }

    //Método para crear una instancia de ApiClient
    public static ApiClient getApi() {
        if (api == null) {
            api = new ApiClient();
        }
        return api;

    }


    //Servicios
    //Para que pueda iniciar sesion
    public Propietario login(String mail, final String password) {
        for (Propietario propietario : propietarios) {
            if (propietario.getEmail().equals(mail) && propietario.getContraseña().equals(password)) {
                usuarioActual = propietario;
                return propietario;
            }
        }
        return null;
    }


    //Retorna el usuario que inició Sesión
    public Propietario obtenerUsuarioActual() {
        return usuarioActual;
    }

    //Retorna todas las propiedades del usuario propietario logueado
    public ArrayList<Inmueble> obtnerPropiedades() {
        ArrayList<Inmueble> temp = new ArrayList<>();
        for (Inmueble inmueble : inmuebles) {
            if (inmueble.getPropietario().equals(usuarioActual)) {
                temp.add(inmueble);
            }
        }
        return temp;
    }

    //Lista de inmuebles alquilados actualmente del propietario logueado.
    public ArrayList<Inmueble> obtenerPropiedadesAlquiladas() {
        ArrayList<Inmueble> temp = new ArrayList<>();
        for (Contrato contrato : contratos) {
            if (contrato.getInmueble().getPropietario().equals(usuarioActual)) {
                temp.add(contrato.getInmueble());
            }
        }
        return temp;
    }


//Dado un inmueble retorna el contrato activo de dicho inmueble

    public Contrato obtenerContratoVigente(Inmueble inmueble) {

        for (Contrato contrato : contratos) {
            if (contrato.getInmueble().equals(inmueble)) {
                return contrato;
            }
        }
        return null;
    }

    //Dado un inmueble, retorna el inquilino del ultimo contrato activo de ese inmueble.
    public Inquilino obtenerInquilino(Inmueble inmueble) {
        for (Contrato contrato : contratos) {
            if (contrato.getInmueble().equals(inmueble)) {
                return contrato.getInquilino();
            }
        }
        return null;
    }

    //Dado un Contrato, retorna los pagos de dicho contrato
    public ArrayList<Pago> obtenerPagos(Contrato contratoVer) {
        ArrayList<Pago> temp = new ArrayList<>();
        for (Contrato contrato : contratos) {
            if (contrato.equals(contratoVer)) {
                for (Pago pago : pagos) {
                    if (pago.getContrato().equals(contrato)) {
                        temp.add(pago);
                    }
                }
            }
            break;
        }
        return temp;
    }

    //Actualizar Perfil
    public void actualizarPerfil(Propietario propietario) {
        int posición = propietarios.indexOf(propietario);
        if (posición != -1) {
            propietarios.set(posición, propietario);
        }
    }

    //ActualizarInmueble
    public void actualizarInmueble(Inmueble inmueble) {
        int posicion = inmuebles.indexOf(inmueble);
        if (posicion != -1) {
            inmuebles.set(posicion, inmueble);
        }
    }

    private void cargaDatos() {

        //Propietarios
        Propietario juan = new Propietario(1, 23492012L, "Juan", "Perez", "juan@mail.com", "123", "2664553447", R.drawable.juan);
        Propietario sonia = new Propietario(2, 17495869L, "Sonia", "Lucero", "sonia@mail.com", "123", "266485417", R.drawable.sonia);
        propietarios.add(juan);
        propietarios.add(sonia);

        //Inquilinos
        Inquilino mario = new Inquilino(100, 25340691L, "Mario", "Luna", "Aiello sup.", "luna@mail.com", "2664253411", "Lucero Roberto", "2664851422");
        inquilinos.add(mario);

        //Inmuebles
        Inmueble salon = new Inmueble(501, "Colon 340", "comercial", "salon", 2, 20000, juan, true, "https://static.tokkobroker.com/water_pics/48672409518958021007001498557369851483796412458067065559507141131794940690553.jpg");
        Inmueble casa = new Inmueble(502, "Mitre 800", "particular", "casa", 2, 15000, juan, true, "https://urquiaga.com.ar/wp-content/uploads/2022/12/alquiler-d%C3%BAplex-casa-en-villa-maria-23.jpg");
        Inmueble otraCasa = new Inmueble(503, "Salta 325", "particular", "casa", 3, 17000, sonia, true, "https://static.tokkobroker.com/pictures/101122428761129536704614518789784054135741527868856006138771618986574128666952.jpg");
        Inmueble dpto = new Inmueble(504, "Lavalle 450", "particular", "dpto", 2, 25000, sonia, true, "https://www.buenacuerdo.com.ar/ws/image.php?img=https%3A%2F%2Fredremax-images.s3-us-west-1.amazonaws.com%2Flistings%2F60119e56-e7df-4790-add9-40e9f15ff47d%2Foriginal%2Fedd0a144-a18f-44f7-9597-d727bbe61965.jpg");
        Inmueble casita = new Inmueble(505, "Belgrano 218", "particular", "casa", 5, 90000, sonia, true, "https://imganuncios.mitula.net/casa_4_ambientes_en_excelente_estado_7990016695815686154.jpg");

        inmuebles.add(salon);
        inmuebles.add(casa);
        inmuebles.add(otraCasa);
        inmuebles.add(dpto);
        inmuebles.add(casita);

        //Contratos
        Contrato uno = new Contrato(701, "05/08/2020", "05/08/2023", 17000, mario, otraCasa);
        contratos.add(uno);
        //Pagos
        pagos.add(new Pago(900, 1, uno, 17000, "10/08/2020"));
        pagos.add(new Pago(901, 2, uno, 17000, "10/09/2020"));
        pagos.add(new Pago(902, 3, uno, 17000, "10/10/2020"));


    }
}
