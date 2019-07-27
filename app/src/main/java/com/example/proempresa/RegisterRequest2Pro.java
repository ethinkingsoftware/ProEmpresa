package com.example.proempresa;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest2Pro extends StringRequest {

    private static final String REGISTER_REQUEST_URJ2="http://192.168.69.2/Register2Pro.php";
    private Map<String,String> params;
    public RegisterRequest2Pro (String nombreEmpresa, String nit, String actividad,
                             String fecha1,String ubicacion, String telefono,String correo,String representante,
                                String capital1, String capital2, Response.Listener<String> listener){
        super (Method.POST, REGISTER_REQUEST_URJ2, listener, null);
        params=new HashMap<>();
        params.put("nombreEmpresa",nombreEmpresa);
        params.put("nit",nit);
        params.put("actividad",actividad);
        params.put("fecha1",fecha1);
        params.put("ubicacion",ubicacion);
        params.put("telefono",telefono);
        params.put("correo",correo);
        params.put("representante",representante);
        params.put("capital1",capital1);
        params.put("capital2",capital2);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
