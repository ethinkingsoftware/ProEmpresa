package com.example.proempresa;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.PlayerViewHolder> {

    private Context mCtx;
    private List<Players2> playerList2;


    public Adapter2(Context mCtx,List<Players2> playerList2){
        this.mCtx = mCtx;
        this.playerList2 = playerList2;
    }


    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout2,null);
        return new PlayerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder,int position){
        Players2 player2 = playerList2.get(position);

        holder.textViewUsername.setText(player2.getUsername());
        holder.textViewEmpresaI.setText(player2.getEmpresaI());
        holder.textViewValorT.setText(player2.getValorT());
        holder.textViewFecha3.setText(player2.getFecha3());
    }

    @Override
    public int getItemCount () {return playerList2.size();}

    class PlayerViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUsername,textViewEmpresaI, textViewValorT,textViewFecha3;

        public  PlayerViewHolder(View itemView){
            super(itemView);

            textViewUsername = itemView.findViewById(R.id.textUsuario);
            textViewEmpresaI = itemView.findViewById(R.id.textEmpresaI);
            textViewValorT = itemView.findViewById(R.id.textValorT);
            textViewFecha3 = itemView.findViewById(R.id.textFecha3);
        }

    }

}

