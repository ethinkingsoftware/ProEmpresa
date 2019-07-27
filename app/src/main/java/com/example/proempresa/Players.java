package com.example.proempresa;

class Players {

    private int id;
    private String nombreEmpresa;
    private String nit;
    private String actividad;
    private String fecha1;
    private String ubicacion;
    private String telefono;
    private String correo;
    private String representante;
    private String capital1;
    private String capital2;

    public Players(int id,String nombreEmpresa,String nit,String actividad,String fecha1,String ubicacion,String telefono,
                   String correo,String representante,String capital1,String capital2){
        this.id=id;
        this.nombreEmpresa=nombreEmpresa;
        this.nit=nit;
        this.actividad=actividad;
        this.fecha1=fecha1;
        this.ubicacion=ubicacion;
        this.telefono=telefono;
        this.correo=correo;
        this.representante=representante;
        this.capital1=capital1;
        this.capital2=capital2;
    }

    public int getId() { return id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    public  String getNit() {
        return nit;
    }
    public String getActividad() {
        return actividad;
    }
    public  String getFecha1() {
        return fecha1;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public  String getTelefono() {
        return telefono;
    }
    public String getCorreo() {
        return correo;
    }
    public  String getRepresentante() {
        return representante;
    }
    public String getCapital1() {
        return capital1;
    }
    public  String getCapital2() {
        return capital2;
    }


}
