package com.example.proempresa;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest3Pro extends StringRequest {

    private static final String REGISTER_REQUEST_URJ3="http://192.168.69.2/Register3Pro.php";
    private Map<String,String> params;
    public RegisterRequest3Pro (String username,String empresaI,String valorT,String fecha3, Response.Listener<String> listener){
        super (Method.POST, REGISTER_REQUEST_URJ3, listener, null);
        params=new HashMap<>();
        params.put("username",username);
        params.put("empresaI",empresaI);
        params.put("valorT",valorT);
        params.put("fecha3",fecha3);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

