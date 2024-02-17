package com.jaio360.view;

import com.jaio360.domain.ProyectoInfo;
import com.jaio360.domain.UsuarioInfo;
import com.jaio360.utils.Utilitarios;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;

import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

import org.apache.log4j.Logger;
import org.apache.commons.logging.LogFactory;

@ManagedBean(name = "stepOneView")
@ViewScoped

public class stepOneView extends BaseView implements Serializable {

    private static Logger log = Logger.getLogger(stepOneView.class);

    private static final long serialVersionUID = -1L;

    private ProyectoInfo objProyectoInfo;

    private UsuarioInfo objUsuarioInfoProxy;

    private Integer activeIndex;

    public ProyectoInfo getObjProyectoInfo() {
        return objProyectoInfo;
    }

    public void setObjProyectoInfo(ProyectoInfo objProyectoInfo) {
        this.objProyectoInfo = objProyectoInfo;
    }

    public UsuarioInfo getObjUsuarioInfoProxy() {
        return objUsuarioInfoProxy;
    }

    public void setObjUsuarioInfoProxy(UsuarioInfo objUsuarioInfoProxy) {
        this.objUsuarioInfoProxy = objUsuarioInfoProxy;
    }

    public Integer getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(Integer activeIndex) {
        this.activeIndex = activeIndex;
    }

    @PostConstruct
    public void init() {
        objProyectoInfo = Utilitarios.obtenerProyecto();

        if (Utilitarios.obtenerUsuarioProxy() != null) {
            objUsuarioInfoProxy = Utilitarios.obtenerUsuario();
        }

        if (Utilitarios.obtenerProyecto() != null) {
            try {
                activeIndex = 0;
            } catch (Exception ex) {
                mostrarError(log, ex);
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("admProjects.jsf");
            } catch (IOException ex) {
                mostrarError(log, ex);
            }

        }
    }

    public void nextStep() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("stepTwo.jsf");
        } catch (IOException ex) {
            log.error(ex);
        }
    }

    public void backStep() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("stepOne.jsf");
        } catch (IOException ex) {
            log.error(ex);
        }
    }

    public void goToStep(Integer intStep) {

        try {

            switch (intStep) {
                case 1:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("stepOne.jsf");
                    break;
                case 2:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("stepTwo.jsf");
                    break;
                case 3:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("stepThree.jsf");
                    break;
                case 4:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("stepFour.jsf");
                    break;
                case 5:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("stepFive.jsf");
                    break;
                case 6:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("stepSix.jsf");
                    break;
            }

        } catch (IOException ex) {
            log.error(ex);
        }

    }

}
