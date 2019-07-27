package com.example.proempresa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.PlayerViewHolder> {

    private Context mCtx;
    private List<Players> playerList;

    private static String URL_RUTA = "http://192.168.69.2/pruebaPro/uploadsPro/";
    private static String resto = ".png";
    private static String direccion;

    public Adapter(Context mCtx,List<Players> playerList){
        this.mCtx = mCtx;
        this.playerList = playerList;
    }


    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout,null);
        return new PlayerViewHolder(view);


    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder,int position){
        Players player = playerList.get(position);

        holder.textViewNombreEmpresa.setText(player.getNombreEmpresa());
        holder.textViewNit.setText(player.getNit());
        holder.textViewActividad.setText(player.getActividad());
        holder.textViewFecha1.setText(player.getFecha1());
        holder.textViewUbicacion.setText(player.getUbicacion());
        holder.textViewTelefono.setText(player.getTelefono());
        holder.textViewCorreo.setText(player.getCorreo());
        holder.textViewRepresentante.setText(player.getRepresentante());
        holder.textViewCapital1.setText(player.getCapital1());
        holder.textViewCapital2.setText(player.getCapital2());

        direccion = URL_RUTA+player.getNombreEmpresa()+resto;

        Glide.with(mCtx)
                .load(direccion)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount () {return playerList.size();}

    class PlayerViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        TextView textViewNombreEmpresa,textViewNit, textViewActividad,textViewFecha1, textViewUbicacion,textViewTelefono,
                textViewCorreo,textViewRepresentante, textViewCapital1, textViewCapital2;
        Button btn_principal;

        public  PlayerViewHolder(View itemView){
            super(itemView);

            textViewNombreEmpresa = itemView.findViewById(R.id.textNombreEmpresa);
            textViewNit = itemView.findViewById(R.id.textNit);
            textViewActividad = itemView.findViewById(R.id.textActividad);
            textViewFecha1 = itemView.findViewById(R.id.textFecha1);
            textViewUbicacion = itemView.findViewById(R.id.textUbicacion);
            textViewTelefono = itemView.findViewById(R.id.textTelefono);
            textViewCorreo= itemView.findViewById(R.id.textCorreo);
            textViewRepresentante = itemView.findViewById(R.id.textRepresentante);
            textViewCapital1 = itemView.findViewById(R.id.textCapital1);
            textViewCapital2 = itemView.findViewById(R.id.textCapital2);
            imageView = itemView.findViewById(R.id.imageView);
            btn_principal = itemView.findViewById(R.id.btn_principal);
        }

    }

}
