package com.example.proempresa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MostrarPacientes extends AppCompatActivity {

    private static final String URL_players3 = "http://192.168.69.2/playersPro.php";
    private static final String URL_playersMonto = "http://192.168.69.2/playersProMonto.php";

    List<Players> playerList;
    List<PlayersM> playerListmonto;

    RecyclerView recyclerView,recyclerViewM;

    public String[] listaEmpresas = new String[60];
    public int[] montoEmpresas = new int[60];
    public int tamanio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_pacientes);

        recyclerView =  findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewM = findViewById(R.id.recyclerViewM);
        recyclerViewM.setHasFixedSize(true);
        recyclerViewM.setLayoutManager(new LinearLayoutManager(this));

        playerList = new ArrayList<>();
        playerListmonto = new ArrayList<>();

        loadplayers();
        loadmonto();

    }

    private void loadplayers() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_players3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            tamanio = array.length();

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject player = array.getJSONObject(i);

                                playerList.add(new Players(
                                        player.getInt("id"),player.getString("nombreEmpresa"),
                                        player.getString("nit"), player.getString("actividad"),
                                        player.getString("fecha1"),player.getString("ubicacion"),
                                        player.getString("telefono"),player.getString("correo"),
                                        player.getString("representante"),player.getString("capital1"),
                                        player.getString("capital2")));

                                listaEmpresas[i]=player.getString("nombreEmpresa");
                            }

                            Adapter adapter = new Adapter(MostrarPacientes.this, playerList);
                            recyclerView.setAdapter(adapter);
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

    private  void loadmonto(){

        StringRequest stringRequestM = new StringRequest(Request.Method.GET, URL_playersMonto,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseM) {
                        try {
                            JSONArray array = new JSONArray(responseM);

                            int entero;
                            String enteroR;


                            for (int j = 0; j < tamanio ; j++){

                              for (int i = 0; i < array.length(); i++) {
                                JSONObject playerM = array.getJSONObject(i);

                                String empresaM = playerM.getString("empresaI");

                                if (listaEmpresas[j].equals(empresaM)){

                                    enteroR = playerM.getString("valorT");
                                    entero = Integer.parseInt(enteroR);
                                    montoEmpresas[j] = montoEmpresas[j] + entero;



                                    //playerListmonto.add(new PlayersM(
                                           // playerM.getInt("id"),playerM.getString("username"),
                                           // playerM.getString("empresaI"), playerM.getString("valorT"),
                                           // playerM.getString("fecha3")));


                                }
                              }
                                int empresasMF=montoEmpresas[j];

                                playerListmonto.add(new PlayersM(empresasMF,listaEmpresas[j]));
                            }

                            AdapterM adapterM = new AdapterM(MostrarPacientes.this, playerListmonto);
                            recyclerViewM.setAdapter(adapterM);
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

        Volley.newRequestQueue(this).add(stringRequestM);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuprincipal,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.opcion1:
                Intent intent = new Intent(MostrarPacientes.this, MainActivity.class);
                intent.putExtra("listaEmpresas",listaEmpresas);
                intent.putExtra("tamanio",tamanio);
                MostrarPacientes.this.startActivity(intent);
                Toast.makeText(getApplicationContext(),"OPCION 1", Toast.LENGTH_LONG).show();
                return true;
            case R.id.opcion2:
                Intent intent2 = new Intent(MostrarPacientes.this, Registro.class);
                intent2.putExtra("listaEmpresas",listaEmpresas);
                intent2.putExtra("tamanio",tamanio);
                MostrarPacientes.this.startActivity(intent2);
                Toast.makeText(getApplicationContext(),"OPCION 2", Toast.LENGTH_LONG).show();
                return true;
            case R.id.opcion3:
                Intent intent3 = new Intent(MostrarPacientes.this, SubirInforma.class);
                MostrarPacientes.this.startActivity(intent3);
                Toast.makeText(getApplicationContext(),"OPCION 3", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void nxt(View v){
        Intent intent = new Intent(MostrarPacientes.this, MainActivity.class);
        intent.putExtra("listaEmpresas",listaEmpresas);
        intent.putExtra("tamanio",tamanio);
        MostrarPacientes.this.startActivity(intent);
    }

}
