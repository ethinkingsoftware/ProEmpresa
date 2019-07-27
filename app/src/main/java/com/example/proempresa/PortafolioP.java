package com.example.proempresa;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class PortafolioP extends AppCompatActivity {

    private static final String URL_playersPortafolio = "http://192.168.69.2/playersPortafolio.php";

    TextView TextUsuarioP;
    TextView SumaT;
    List<Players2> playerList2;

    RecyclerView recyclerView2;

    Bundle b;
    public String[] listaEmpresas = new String[60];
    public int tamanio;
    public String username1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portafolio_p);

        TextUsuarioP = findViewById(R.id.TextUsuarioP);
        SumaT = findViewById(R.id.SumaT);

        b= getIntent().getExtras();
        tamanio=b.getInt("tamanio");
        listaEmpresas=b.getStringArray("listaEmpresas");
        username1=b.getString("username");

        TextUsuarioP.setText(username1);

        recyclerView2 =  findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        playerList2 = new ArrayList<>();

        loadplayers();


    }

    private void loadplayers() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_playersPortafolio,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            int entero;
                            String enteroR;
                            int suma=0;
                            String sumaS;

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject player2 = array.getJSONObject(i);

                                String usuario = player2.getString("username");

                                if (username1.equals(usuario)){

                                playerList2.add(new Players2(
                                        player2.getInt("id"),player2.getString("username"),
                                        player2.getString("empresaI"), player2.getString("valorT"),
                                        player2.getString("fecha3")));

                                enteroR = player2.getString("valorT");
                                entero = Integer.parseInt(enteroR);

                                suma = suma+entero;
                                }
                            }

                            sumaS = String.valueOf(suma);
                            SumaT.setText(sumaS);

                            Adapter2 adapter2 = new Adapter2(PortafolioP.this, playerList2);
                            recyclerView2.setAdapter(adapter2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);

    }
}
