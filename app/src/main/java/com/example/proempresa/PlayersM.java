package com.example.proempresa;

public class PlayersM {

    private int montoMF;
    private String montoMFS;
    private String nombreEmpresa;
    private int id;
    private String username;
    private String empresaI;
    private String valorT;
    private String fecha3;


    public PlayersM(int montoMF,String nombreEmpresa){
        this.nombreEmpresa=nombreEmpresa;
        this.montoMF=montoMF;
        montoMFS = String.valueOf(montoMF);
       // this.id=id;
       // this.username=username;
       // this.empresaI=empresaI;
       // this.valorT=valorT;
       // this.fecha3=fecha3;
    }

    public String getMontoMFS(){
        return montoMFS;
    }

    public String getNombreEmpresaA(){
        return nombreEmpresa;
    }

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public  String getEmpresaI() {
        return empresaI;
    }
    public  String getValorT() {
        return valorT;
    }
    public  String getFecha3() {
        return fecha3;
    }
}
