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
    private String senhaAntiga;
    private String senhaNova;

    public String login2() {
        context = FacesContext.getCurrentInstance();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        this.usuario = usuarioDAO.buscarPorEmail(this.nomeUsuario);
        String hash = usuario.getSeguranca().getSALT();
        String senhaCompleta = HashUtil.generateHash(this.senha, hash);
        this.usuario = usuarioDAO.autentica(nomeUsuario, senhaCompleta);
        if (usuario != null) {
            System.out.println("USUARIO LOGADO: " + usuario.getEmail() + " ---- " + usuario.getNome());
            System.out.println("CEP E TEL: " + usuario.getEndereco().getCEP() + " ---- " + usuario.getEndereco().getUF() + " ---- " + usuario.getTelefone());
            return "/painelProfissional?faces-redirect=true";

        } else {
            System.out.println("LOGIN OU SENHA INVALIDOS");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "USUARIO OU SENHA INVALIDOS", ""));
            return null;
        }

    }

    public void alteraSenha() {

        String toCompare = HashUtil.generateHash(this.senhaAntiga, this.usuario.getSeguranca().getSALT());
        String newpass;
        
        System.out.println("HASH ANTIGO : " + this.usuario.getSenha());
        System.out.println("HASH NOVO   : " + toCompare);
        if (toCompare.equals(this.usuario.getSenha())) {
            newpass = HashUtil.generateHash(this.senhaNova, this.usuario.getSeguranca().getSALT());
            this.usuario.setSenha(newpass);
            System.out.println("SENHA ALTERADA");
        }

    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/home?faces-redirect=true";
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

    public FacesContext getContext() {
        return context;
    }

    public void setContext(FacesContext context) {
        this.context = context;
    }

    public String getSenhaAntiga() {
        return senhaAntiga;
    }

    public void setSenhaAntiga(String senhaAntiga) {
        this.senhaAntiga = senhaAntiga;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public void setSenhaNova(String senhaNova) {
        this.senhaNova = senhaNova;
    }

}
