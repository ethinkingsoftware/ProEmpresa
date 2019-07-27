package com.example.proempresa;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    Bundle b;
    public String[] listaEmpresas = new String[60];
    public int tamanio;

    Button btn_registrar1;
    EditText et_usuario000;
    EditText et_password000;
    EditText et_password001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        b= getIntent().getExtras();
        tamanio=b.getInt("tamanio");
        listaEmpresas=b.getStringArray("listaEmpresas");

        et_usuario000= findViewById(R.id.Text_usuario000);
        et_password000= findViewById(R.id.Text_password000);
        et_password001= findViewById(R.id.Text_password001);

        btn_registrar1 = (Button) findViewById(R.id.Btn_registrar1);

        btn_registrar1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        final String username= et_usuario000.getText().toString();
         final String password= et_password000.getText().toString();
         final String password1= et_password001.getText().toString();

        if (password.equals(password1) ){

            Response.Listener<String> respoListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonReponse = new JSONObject(response);
                        boolean success=jsonReponse.getBoolean("success");
                        if(success){
                            Intent intent = new Intent(Registro.this,MainActivity.class);
                            intent.putExtra("listaEmpresas",listaEmpresas);
                            intent.putExtra("tamanio",tamanio);
                            Registro.this.startActivity(intent);
                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                            builder.setMessage("ERROR REGISTRO")
                                    .setNegativeButton("Retry",null)
                                    .create().show();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            RegisterRequestPro registerRequestPro= new RegisterRequestPro(username, password,respoListener) ;

            RequestQueue queue = Volley.newRequestQueue(Registro.this);
            queue.add(registerRequestPro);

        }else {
            Toast.makeText(this,"claves no concuerdan",Toast.LENGTH_LONG).show();
        }
    }


}

