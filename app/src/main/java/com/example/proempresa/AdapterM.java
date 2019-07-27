package com.example.proempresa;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterM extends RecyclerView.Adapter<AdapterM.PlayerViewHolder> {

    private Context mCtxM;
    private List<PlayersM> playerListmonto;


    public AdapterM(Context mCtxM,List<PlayersM> playerListmonto){
        this.mCtxM = mCtxM;
        this.playerListmonto = playerListmonto;
    }


    @Override
    public AdapterM.PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mCtxM);
        View view = inflater.inflate(R.layout.list_layoutmonto,null);
        return new AdapterM.PlayerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(AdapterM.PlayerViewHolder holder, int position){
        PlayersM playersM = playerListmonto.get(position);

        holder.textMontoA.setText(playersM.getMontoMFS());
        holder.textNombreEmpresaA.setText(playersM.getNombreEmpresaA());
        //holder.textViewUsername.setText(playersM.getUsername());
        //holder.textViewEmpresaI.setText(playersM.getEmpresaI());
        //holder.textViewValorT.setText(playersM.getValorT());
        //holder.textViewFecha3.setText(playersM.getFecha3());
    }

    @Override
    public int getItemCount () {return playerListmonto.size();}

    class PlayerViewHolder extends RecyclerView.ViewHolder {

        TextView textMontoA,textNombreEmpresaA;
        //TextView textViewUsername,textViewEmpresaI, textViewValorT,textViewFecha3;

        public  PlayerViewHolder(View itemView){
            super(itemView);

            textMontoA = itemView.findViewById(R.id.textMontoA);
            textNombreEmpresaA = itemView.findViewById(R.id.textNombreEmpresaA);
            //textViewUsername = itemView.findViewById(R.id.textUsuario);
            //textViewEmpresaI = itemView.findViewById(R.id.textEmpresaI);
            //textViewValorT = itemView.findViewById(R.id.textValorT);
            //textViewFecha3 = itemView.findViewById(R.id.textFecha3);
        }

    }

}
