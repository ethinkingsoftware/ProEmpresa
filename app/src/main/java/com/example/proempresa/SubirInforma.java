package com.example.proempresa;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class SubirInforma extends AppCompatActivity implements View.OnClickListener{

    EditText etnombreEmpresa, ettelefono,etnit, etubicacion, etactividad, etcorreo, etrepresentante;
    EditText etfecha1,etminimoCapital,etmaximoCapital;
    Button btn_registrar2;


    private int dia,anio,mes;

    TextView TvUsuario2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_informa);

        etfecha1 = findViewById(R.id.Text_fecha1);
        btn_registrar2 = findViewById(R.id.Btn_registrar2);
        etnombreEmpresa= findViewById(R.id.Text_nombreEmpresa);
        etnit= findViewById(R.id.Text_nit);
        etactividad= findViewById(R.id.Text_actividad);
        etubicacion= findViewById(R.id.Text_ubicacion);
        etrepresentante= findViewById(R.id.Text_representante);
        ettelefono= findViewById(R.id.Text_telefono);
        etcorreo= findViewById(R.id.Text_correo);
        etminimoCapital= findViewById(R.id.Text_montoI);
        etmaximoCapital= findViewById(R.id.Text_montoF);

        btn_registrar2.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {




            final String nombreEmpresa = etnombreEmpresa.getText().toString();
            final String nit = etnit.getText().toString();
            final String actividad = etactividad.getText().toString();
            final String fecha1 = etfecha1.getText().toString();
            final String ubicacion = etubicacion.getText().toString();
            final String telefono = ettelefono.getText().toString();
            final String correo = etcorreo.getText().toString();
            final String representante = etrepresentante.getText().toString();
            final String capital1 = etminimoCapital.getText().toString();
            final String capital2 = etmaximoCapital.getText().toString();

            Response.Listener<String> respoListener2 = new Response.Listener<String>() {
            @Override
            public void onResponse(String response2) {
                try {

                    JSONObject jsonReponse2 = new JSONObject(response2);
                    boolean success = jsonReponse2.getBoolean("success");
                    if (success) {
                        Intent intent = new Intent(SubirInforma.this, Foto.class);
                        intent.putExtra("nombreEmpresa",nombreEmpresa);
                        SubirInforma.this.startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SubirInforma.this);
                        builder.setMessage("ERROR REGISTRO2")
                                .setNegativeButton("Retry", null)
                                .create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RegisterRequest2Pro registerRequest2Pro = new RegisterRequest2Pro(nombreEmpresa,nit, actividad,
                fecha1, ubicacion, telefono, correo, representante, capital1, capital2, respoListener2);

        RequestQueue queue2 = Volley.newRequestQueue(SubirInforma.this);
        queue2.add(registerRequest2Pro);

            finish();
        }


}
