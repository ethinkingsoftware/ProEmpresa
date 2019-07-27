package com.example.proempresa;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class Foto extends AppCompatActivity {



    private String nombreEmpresa;

    private String  APP_DIRECTORY = "myPictureApp/";
    private String MEDIA_DIRECTORY = APP_DIRECTORY + "media";
    private String TEMPORAL_PICTURE_NAME = "temporal.jpg";

    Bitmap bitmap;

    String UPLOAD_URL = "http://192.168.69.2/pruebaPro/uploadPro.php";

    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;

    private ImageView imageView;
    private String identificacion;

    String KEY_IMAGE = "foto";
    String KEY_NOMBRE = "nombre";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

        Intent intent = getIntent();
        nombreEmpresa = intent.getStringExtra("nombreEmpresa");


        imageView = (ImageView) findViewById(R.id.setPicture);
        Button button = (Button) findViewById(R.id.buttonImage);
        Button btnSubir = (Button) findViewById(R.id.SubirImagen);
        Button btnMenu = (Button) findViewById(R.id.Menu);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] options = {"tomar foto","elegir de galeria","cancelar","prueba"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(Foto.this);
                builder.setTitle("ELIGE UNA OPCION");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int seleccion) {
                        if(options[seleccion]=="tomar foto"){
                            openCamera();
                        }else if (options[seleccion]=="elegir de galeria"){
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(intent.createChooser(intent,"selecciona appa de imagen"),SELECT_PICTURE);
                        }else if (options[seleccion]=="cancelar"){
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });

        btnSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImagen();
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Foto.this,MostrarPacientes.class);
                Foto.this.startActivity(intent);
                finish();
            }
        });

    }



    //CONVIERTE LA IMAGEN EN UN STRING
    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] imagenBytes = baos.toByteArray();
        String encodedImagen = Base64.encodeToString(imagenBytes,Base64.DEFAULT);

        return encodedImagen;
    }

    //SUBIR IMAGEN
    public void uploadImagen(){
        final ProgressDialog cargando = ProgressDialog.show(this,"Subiendo ...","Espere por favor");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                cargando.dismiss();
                Toast.makeText(Foto.this, response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                cargando.dismiss();
                Toast.makeText(Foto.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                String imagen = getStringImagen(bitmap);
                String identificacion2 = identificacion;

                Map<String,String> params = new Hashtable<String, String>();
                params.put(KEY_IMAGE,imagen);
                params.put(KEY_NOMBRE, nombreEmpresa);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(),MEDIA_DIRECTORY);
        file.mkdirs();

        String path = Environment.getExternalStorageDirectory()+File.separator+MEDIA_DIRECTORY
                +File.separator+TEMPORAL_PICTURE_NAME;

        File newFile = new File(path);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
        startActivityForResult(intent,PHOTO_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case PHOTO_CODE:
                if (resultCode==RESULT_OK){
                    String dir = Environment.getExternalStorageDirectory()+File.separator+MEDIA_DIRECTORY
                            +File.separator+TEMPORAL_PICTURE_NAME;
                    decodeBitmap(dir);
                }
                break;

            case SELECT_PICTURE:
                if (resultCode==RESULT_OK){
                    Uri path = data.getData();
                    try {
                        //COMO OBTENER EL MAPA DE BITS DE LA GALERIA
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                        //CONFIGURACION DEL MAPA DE BITS EN IMAGEVIEW
                        imageView.setImageBitmap(bitmap);
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    //Uri path = data.getData();
                    //imageView.setImageURI(path);
                }
                break;
        }
    }

    private void decodeBitmap(String dir) {

        bitmap = BitmapFactory.decodeFile(dir);
        imageView.setImageBitmap(bitmap);
    }
}
