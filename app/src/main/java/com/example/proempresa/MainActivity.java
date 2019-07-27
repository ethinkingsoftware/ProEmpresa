package com.example.proempresa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Bundle b;
    public String[] listaEmpresas = new String[60];
    public int tamanio;

    Button btn_registrar;
    EditText et_usuario;
    EditText et_password;
    Button btn_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b= getIntent().getExtras();
        tamanio=b.getInt("tamanio");
        listaEmpresas=b.getStringArray("listaEmpresas");


        btn_registrar = (Button) findViewById(R.id.Btn_registrar);
        et_usuario = (EditText) findViewById(R.id.et_usuario);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_log = (Button) findViewById(R.id.btn_log);

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(MainActivity.this, Registro.class);
                intentReg.putExtra("listaEmpresas",listaEmpresas);
                intentReg.putExtra("tamanio",tamanio);
                MainActivity.this.startActivity(intentReg);
            }
        });

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String username= et_usuario.getText().toString();
                final String password= et_password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponce = new JSONObject(response);
                            boolean success = jsonResponce.getBoolean("success");
                            if (success){
                                Intent intent = new Intent(MainActivity.this,Usuario.class);
                                intent.putExtra("listaEmpresas",listaEmpresas);
                                intent.putExtra("tamanio",tamanio);
                                intent.putExtra("username",username);
                                MainActivity.this.startActivity(intent);
                                et_password.setText("");
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("ERROR LOGIN")
                                        .setNegativeButton("Retry",null)
                                        .create().show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                LoginRequestPro loginRequestPro = new LoginRequestPro(username,password,responseListener);

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequestPro);

            }

        });

    }
}
