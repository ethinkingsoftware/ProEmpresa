package com.example.proempresa;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequestPro extends StringRequest {

    private static final String REGISTER_REQUEST_URJ="http://192.168.69.2/RegisterPro.php";
    private Map<String,String> params;
    public RegisterRequestPro ( String username,String password,Response.Listener<String> listener){
        super (Method.POST, REGISTER_REQUEST_URJ, listener, null);
        params=new HashMap<>();
        params.put("username",username);
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
