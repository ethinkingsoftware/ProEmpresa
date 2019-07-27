package com.example.proempresa;

public class Players2 {

    private int id;
    private String username;
    private String empresaI;
    private String valorT;
    private String fecha3;


    public Players2(int id,String username,String empresaI,String valorT,String fecha3){
        this.id=id;
        this.username=username;
        this.empresaI=empresaI;
        this.valorT=valorT;
        this.fecha3=fecha3;
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
