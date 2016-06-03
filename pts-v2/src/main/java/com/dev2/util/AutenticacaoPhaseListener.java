/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.util;

import com.dev2.controller.LoginBean;
import java.util.Map;
import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author 10070094
 */
public class AutenticacaoPhaseListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        UIViewRoot uiViewRoot = facesContext.getViewRoot();
        String paginaAtual = uiViewRoot.getViewId();
        //System.out.println("PAGINA ATUAL: " + paginaAtual);

        boolean ehPaginaAutenticacao = paginaAtual.contains("login.xhtml");
        boolean ehPaginaCadastro = paginaAtual.contains("index.xhtml");
         boolean ehPaginahome = paginaAtual.contains("home.xhtml");
         boolean ehPaginaForgotMyPass = paginaAtual.contains("esqueciSenha.xhtml");
        //System.out.println(ehPaginaAutenticacao);

        if (!ehPaginaAutenticacao && !ehPaginaCadastro && !ehPaginahome && !ehPaginaForgotMyPass) {
            ExternalContext externalContext = facesContext.getExternalContext();
            Map<String, Object> mapa = externalContext.getSessionMap();
            LoginBean loginBean = (LoginBean) mapa.get("loginBean");

            System.out.println("USUARIO: " + loginBean.getNomeUsuario());

            if (loginBean.getUsuario() == null) {
                System.out.println("USUARIO NAO AUTENTICADO");
                Application application = facesContext.getApplication();
                NavigationHandler navigationHandler = application.getNavigationHandler();
                navigationHandler.handleNavigation(facesContext, null, "/login.xhtml?faces-redirect=true");

            }

        }
    }

    @Override
    public void beforePhase(PhaseEvent pe) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
