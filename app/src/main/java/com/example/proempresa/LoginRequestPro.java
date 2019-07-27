package com.example.proempresa;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequestPro extends StringRequest {


    private static final String LOGIN_REQUEST_URJ="http://192.168.69.2/LoginPro.php";
    private Map<String,String> params;
    public LoginRequestPro ( String username, String password, Response.Listener<String> listener){
        super (Request.Method.POST, LOGIN_REQUEST_URJ, listener,null);
        params=new HashMap<>();
        params.put("username",username);
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
