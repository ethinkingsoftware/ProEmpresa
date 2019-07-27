package com.example.proempresa;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Transaccion extends AppCompatActivity {

    Bundle b;
    public String[] listaEmpresas = new String[60];
    public int tamanio;
    public String username;
    public String empresaI;
    TextView TvUsuario1;
    TextView TvEmpresa1;
    Button sea_food,steel_construction,jewlery_trade,pharmaceutical_products,organic_fertilizer,distribution_agreements;
    public String ids_valor[] = {"300","1000","3000","6000","10000","20000","60000","100000","200000","500000"};
    public String valorT;
    public String fecha3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaccion);

        b= getIntent().getExtras();
        tamanio=b.getInt("tamanio");
        listaEmpresas=b.getStringArray("listaEmpresas");
        username=b.getString("username");
        empresaI=b.getString("empresaI");
        TvUsuario1=findViewById(R.id.TvUsuario1);
        TvEmpresa1=findViewById(R.id.TvEmpresa1);
        TvUsuario1.setText(username);
        TvEmpresa1.setText(empresaI);
        findViewById(R.id.sea_food).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.mercadopago.com.co/checkout/v1/redirect/81ac3874-4c7f-4789-b778-e5fd188f52b8/payment-option-form/?preference-id=273032454-07f4cc63-f899-460d-8578-78233920d282#/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        findViewById(R.id.steel_construction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.mercadopago.com.co/checkout/v1/redirect/408d89dd-bde1-453d-bcec-263f4d2127a6/payment-option-form/?preference-id=273032454-bac1170f-a27a-41e1-9b26-43011094e963#/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        findViewById(R.id.jewlery_trade).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.mercadopago.com.co/checkout/v1/redirect/10de25ca-3056-4978-af15-264215d37a7a/payment-option-form/?preference-id=273032454-78f9c6d6-4d2a-4ae2-8b28-8071a84b916f#/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        findViewById(R.id.pharmaceutical_products).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.mercadopago.com.co/checkout/v1/redirect/9ca046af-a398-47dd-8078-17a253fdf91d/payment-option-form/?preference-id=273032454-68913b86-17be-42f8-93b3-2492c7f9d953#/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        findViewById(R.id.organic_fertilizer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.mercadopago.com.co/checkout/v1/redirect/df36c985-c1ee-411e-83fe-482ecfe253e7/payment-option-form/?preference-id=273032454-244ea1ba-f613-4cee-aaaa-5d67c6971b63#/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        findViewById(R.id.distribution_agreements).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });




    }
}
