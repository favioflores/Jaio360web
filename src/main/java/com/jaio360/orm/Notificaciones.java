package com.jaio360.orm;
// Generated 21/10/2014 08:38:36 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Notificaciones generated by hbm2java
 */
public class Notificaciones  implements java.io.Serializable {


     private Integer noIdNotificacionPk;
     private int noIdRefProceso;
     private int noIdTipoProceso;
     private Date noFeCreacion;
     private Date noFeEnvio;
     private int noIdEstado;
     private String noTxAsunto;
     private byte[] noTxMensaje;
     private String noTxError;
     private String noAdjunto;
     private Set destinatarioses = new HashSet(0);

    public Notificaciones() {
    }

	
    public Notificaciones(int noIdRefProceso, int noIdTipoProceso, Date noFeCreacion, int noIdEstado, String noTxAsunto, byte[] noTxMensaje) {
        this.noIdRefProceso = noIdRefProceso;
        this.noIdTipoProceso = noIdTipoProceso;
        this.noFeCreacion = noFeCreacion;
        this.noIdEstado = noIdEstado;
        this.noTxAsunto = noTxAsunto;
        this.noTxMensaje = noTxMensaje;
    }
    public Notificaciones(int noIdRefProceso, int noIdTipoProceso, Date noFeCreacion, Date noFeEnvio, int noIdEstado, String noTxAsunto, byte[] noTxMensaje, String noAdjunto, Set destinatarioses) {
       this.noIdRefProceso = noIdRefProceso;
       this.noIdTipoProceso = noIdTipoProceso;
       this.noFeCreacion = noFeCreacion;
       this.noFeEnvio = noFeEnvio;
       this.noIdEstado = noIdEstado;
       this.noTxAsunto = noTxAsunto;
       this.noTxMensaje = noTxMensaje;
       this.noAdjunto = noAdjunto;
       this.destinatarioses = destinatarioses;
    }
   
    public Integer getNoIdNotificacionPk() {
        return this.noIdNotificacionPk;
    }
    
    public void setNoIdNotificacionPk(Integer noIdNotificacionPk) {
        this.noIdNotificacionPk = noIdNotificacionPk;
    }
    public int getNoIdRefProceso() {
        return this.noIdRefProceso;
    }
    
    public void setNoIdRefProceso(int noIdRefProceso) {
        this.noIdRefProceso = noIdRefProceso;
    }
    public int getNoIdTipoProceso() {
        return this.noIdTipoProceso;
    }
    
    public void setNoIdTipoProceso(int noIdTipoProceso) {
        this.noIdTipoProceso = noIdTipoProceso;
    }
    public Date getNoFeCreacion() {
        return this.noFeCreacion;
    }
    
    public void setNoFeCreacion(Date noFeCreacion) {
        this.noFeCreacion = noFeCreacion;
    }
    public Date getNoFeEnvio() {
        return this.noFeEnvio;
    }
    
    public void setNoFeEnvio(Date noFeEnvio) {
        this.noFeEnvio = noFeEnvio;
    }
    public int getNoIdEstado() {
        return this.noIdEstado;
    }
    
    public void setNoIdEstado(int noIdEstado) {
        this.noIdEstado = noIdEstado;
    }
    public String getNoTxAsunto() {
        return this.noTxAsunto;
    }
    
    public void setNoTxAsunto(String noTxAsunto) {
        this.noTxAsunto = noTxAsunto;
    }
    public byte[] getNoTxMensaje() {
        return this.noTxMensaje;
    }
    
    public void setNoTxMensaje(byte[] noTxMensaje) {
        this.noTxMensaje = noTxMensaje;
    }
    public String getNoAdjunto() {
        return this.noAdjunto;
    }
    
    public void setNoAdjunto(String noAdjunto) {
        this.noAdjunto = noAdjunto;
    }
    public Set getDestinatarioses() {
        return this.destinatarioses;
    }
    
    public void setDestinatarioses(Set destinatarioses) {
        this.destinatarioses = destinatarioses;
    }

    public String getNoTxError() {
        return noTxError;
    }

    public void setNoTxError(String noTxError) {
        this.noTxError = noTxError;
    }

}


