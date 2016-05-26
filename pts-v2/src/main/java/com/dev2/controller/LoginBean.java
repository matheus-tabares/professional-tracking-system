/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.controller;

import com.dev2.dao.UsuarioDAO;
import com.dev2.model.Usuario;
import com.dev2.util.HashUtil;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Bruno
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private Usuario usuario;
    private String nomeUsuario;
    private String senha;
    private FacesContext context;

    public String login2() {
        context = FacesContext.getCurrentInstance();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        this.usuario = usuarioDAO.buscarPorEmail(this.nomeUsuario);
        String hash = usuario.getSeguranca().getSALT();
        String senhaCompleta = HashUtil.generateHash(this.senha, hash);
        this.usuario = usuarioDAO.autentica(nomeUsuario, senhaCompleta);
        if (usuario != null) {
            System.out.println("USUARIO LOGADO: " + usuario.getEmail() + " ---- " + usuario.getNome());
            return "/novoEndereco?faces-redirect=true";

        } else {
            System.out.println("LOGIN OU SENHA INVALIDOS");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "USUARIO OU SENHA INVALIDOS", ""));
            return null;
        }

    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
