package com.example.proempresa;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Usuario extends AppCompatActivity {

    Bundle b;
    public String[] listaEmpresas = new String[60];
    public int tamanio;

    TextView TvUsuario;
    public String username;
    public String empresaI;

    LinearLayout contenedor;
    public Button btn_portafolio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        b= getIntent().getExtras();
        tamanio=b.getInt("tamanio");
        listaEmpresas=b.getStringArray("listaEmpresas");
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        TvUsuario = findViewById(R.id.Text_usuario);
        TvUsuario.setText(username);
        contenedor = findViewById(R.id.contenedor);
        btn_portafolio =  findViewById(R.id.btn_portafolio);

        ArrayList<check>  lista = new ArrayList<check>();
        for (int i = 0; i < tamanio; i++){
            lista.add(new check(i+1,listaEmpresas[i]));
        }

        btn_portafolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(Usuario.this, PortafolioP.class);
                intentReg.putExtra("listaEmpresas",listaEmpresas);
                intentReg.putExtra("tamanio",tamanio);
                intentReg.putExtra("username",username);
                Usuario.this.startActivity(intentReg);
            }
        });


        for (final check c:lista){
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(c.nombre);
            cb.setId(c.cod);
            cb.setTextColor(Color.BLACK);
            cb.setBackgroundColor(Color.YELLOW);

            contenedor.addView(cb);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    empresaI=c.nombre;
                    Toast.makeText(getApplicationContext(),"Checkbox:" +buttonView.getText() ,Toast.LENGTH_LONG).show();
                    Intent intentReg = new Intent(Usuario.this, Transaccion.class);
                    intentReg.putExtra("listaEmpresas",listaEmpresas);
                    intentReg.putExtra("tamanio",tamanio);
                    intentReg.putExtra("username",username);
                    intentReg.putExtra("empresaI",empresaI);
                    Usuario.this.startActivity(intentReg);
                    finish();
                }
            });
        }

    }

    class check{
        public int cod;
        public String nombre;

        public check(int cod,String nombre){
            this.cod = cod;
            this.nombre = nombre;
        }

    }
}
