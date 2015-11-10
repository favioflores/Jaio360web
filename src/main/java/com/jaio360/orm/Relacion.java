package com.jaio360.orm;
// Generated 21/10/2014 08:38:36 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Relacion generated by hbm2java
 */
public class Relacion  implements java.io.Serializable {


     private Integer reIdRelacionPk;
     private Proyecto proyecto;
     private String reTxNombre;
     private String reTxDescripcion;
     private int reNuOrden;
     private String reTxAbreviatura;
     private String reColor;
     private Integer reIdEstado;
     private Set relacionParticipantes = new HashSet(0);

    public Relacion() {
    }

    public Relacion(Integer reIdRelacionPk, String reTxNombre) {
        this.reIdRelacionPk = reIdRelacionPk;
        this.reTxNombre = reTxNombre;
    }
	
    public Relacion(Proyecto proyecto, String reTxNombre, String reTxDescripcion, int reNuOrden, String reTxAbreviatura, String reColor, Integer reIdEstado) {
        this.proyecto = proyecto;
        this.reTxNombre = reTxNombre;
        this.reTxDescripcion = reTxDescripcion;
        this.reNuOrden = reNuOrden;
        this.reTxAbreviatura = reTxAbreviatura;
        this.reColor = reColor;
        this.reIdEstado = reIdEstado;
    }
    public Relacion(Proyecto proyecto, String reTxNombre, String reTxDescripcion, int reNuOrden, String reTxAbreviatura, String reColor, int reIdEstado, Set relacionParticipantes) {
       this.proyecto = proyecto;
       this.reTxNombre = reTxNombre;
       this.reTxDescripcion = reTxDescripcion;
       this.reNuOrden = reNuOrden;
       this.reTxAbreviatura = reTxAbreviatura;
       this.reColor = reColor;
       this.reIdEstado = reIdEstado;
       this.relacionParticipantes = relacionParticipantes;
    }
   
    public Integer getReIdRelacionPk() {
        return this.reIdRelacionPk;
    }
    
    public void setReIdRelacionPk(Integer reIdRelacionPk) {
        this.reIdRelacionPk = reIdRelacionPk;
    }
    public Proyecto getProyecto() {
        return this.proyecto;
    }
    
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
    public String getReTxNombre() {
        return this.reTxNombre;
    }
    
    public void setReTxNombre(String reTxNombre) {
        this.reTxNombre = reTxNombre;
    }
    public String getReTxDescripcion() {
        return this.reTxDescripcion;
    }
    
    public void setReTxDescripcion(String reTxDescripcion) {
        this.reTxDescripcion = reTxDescripcion;
    }
    public int getReNuOrden() {
        return this.reNuOrden;
    }
    
    public void setReNuOrden(int reNuOrden) {
        this.reNuOrden = reNuOrden;
    }
    public String getReTxAbreviatura() {
        return this.reTxAbreviatura;
    }
    
    public void setReTxAbreviatura(String reTxAbreviatura) {
        this.reTxAbreviatura = reTxAbreviatura;
    }
    public String getReColor() {
        return this.reColor;
    }
    
    public void setReColor(String reColor) {
        this.reColor = reColor;
    }
    public Integer getReIdEstado() {
        return this.reIdEstado;
    }
    
    public void setReIdEstado(Integer reIdEstado) {
        this.reIdEstado = reIdEstado;
    }
    public Set getRelacionParticipantes() {
        return this.relacionParticipantes;
    }
    
    public void setRelacionParticipantes(Set relacionParticipantes) {
        this.relacionParticipantes = relacionParticipantes;
    }




}


