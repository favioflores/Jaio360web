package com.jaio360.orm;
// Generated 21/10/2014 08:38:36 PM by Hibernate Tools 3.2.1.GA



/**
 * Mensaje generated by hbm2java
 */
public class Mensaje  implements java.io.Serializable {


     private Integer meIdMensajePk;
     private Proyecto proyecto;
     private Integer meIdTipoMensaje;
     private String meTxAsunto;
     private byte[] meTxCuerpo;

    public Mensaje() {
    }

	
    public Mensaje(Proyecto proyecto, int meIdTipoMensaje) {
        this.proyecto = proyecto;
        this.meIdTipoMensaje = meIdTipoMensaje;
    }
    public Mensaje(Proyecto proyecto, int meIdTipoMensaje, String meTxAsunto, byte[] meTxCuerpo) {
       this.proyecto = proyecto;
       this.meIdTipoMensaje = meIdTipoMensaje;
       this.meTxAsunto = meTxAsunto;
       this.meTxCuerpo = meTxCuerpo;
    }
   
    public Integer getMeIdMensajePk() {
        return this.meIdMensajePk;
    }
    
    public void setMeIdMensajePk(Integer meIdMensajePk) {
        this.meIdMensajePk = meIdMensajePk;
    }
    public Proyecto getProyecto() {
        return this.proyecto;
    }
    
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
    public Integer getMeIdTipoMensaje() {
        return this.meIdTipoMensaje;
    }
    
    public void setMeIdTipoMensaje(Integer meIdTipoMensaje) {
        this.meIdTipoMensaje = meIdTipoMensaje;
    }
    public String getMeTxAsunto() {
        return this.meTxAsunto;
    }
    
    public void setMeTxAsunto(String meTxAsunto) {
        this.meTxAsunto = meTxAsunto;
    }
    public byte[] getMeTxCuerpo() {
        return this.meTxCuerpo;
    }
    
    public void setMeTxCuerpo(byte[] meTxCuerpo) {
        this.meTxCuerpo = meTxCuerpo;
    }




}


