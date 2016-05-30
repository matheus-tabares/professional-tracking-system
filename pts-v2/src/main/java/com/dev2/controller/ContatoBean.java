/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.controller;

import com.dev2.dao.ContatoDAO;
import com.dev2.dao.UsuarioDAO;
import com.dev2.model.Contato;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Bruno
 */
@ManagedBean
@SessionScoped
public class ContatoBean implements Serializable {

    private Contato contato = new Contato();
    private ContatoDAO contatoDAO = new ContatoDAO();
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    private int idDestinatario;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private List<Contato> mensagensRecebidas; // = contatoDAO.listaMensagensRecebidas(loginBean.getUsuario().getId());
    private List<Contato> mensagensEnviadas; //= contatoDAO.listaMensagensEnviadas(loginBean.getUsuario().getId());

    public String cadastrar() {
        contatoDAO = new ContatoDAO();
        this.contato.setRemetente(loginBean.getUsuario());
        this.contato.setDestinatario(usuarioDAO.carregar(idDestinatario));
        this.contatoDAO.cadastrar(contato);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MENSAGEM ENVIADA", ""));
        this.contato = new Contato();
        return "painelProfissional?faces-redirect=true";
    }

    public String responderMensagem() {
        System.out.println("REMETENTE" + this.contato.getRemetente().getNome());
        System.out.println("DESTINATARIO" + this.contato.getDestinatario().getNome());
        contatoDAO = new ContatoDAO();
        int remententeAntigo = this.contato.getRemetente().getId();
        this.contato.setRemetente(loginBean.getUsuario());
        this.contato.setDestinatario(usuarioDAO.carregar(remententeAntigo));
        System.out.println("REMETENTE" + this.contato.getRemetente().getNome());
        System.out.println("DESTINATARIO" + this.contato.getDestinatario().getNome());

        this.contatoDAO.cadastrar(contato);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "MENSAGEM RESPONDIDA", ""));
        this.contato = new Contato();
        return "painelProfissional?faces-redirect=true";
    }

    /*
    public void getMensagensEnviadas() {
        this.contatoDAO = new ContatoDAO();
        this.mensagensEnviadas = contatoDAO.listaMensagensEnviadas(loginBean.getUsuario().getId());
    }

    public void getMensagensRecebidas() {
        this.contatoDAO = new ContatoDAO();
        this.mensagensRecebidas = contatoDAO.listaMensagensRecebidas(loginBean.getUsuario().getId());
    }*/
    public List<Contato> getMensagensRecebidas() {
        return contatoDAO.listaMensagensRecebidas(loginBean.getUsuario().getId());
    }

    public List<Contato> getMensagensEnviadas() {
        return contatoDAO.listaMensagensEnviadas(loginBean.getUsuario().getId());
    }

    public String detalheContato(int id) {
        contato = contatoDAO.carregar(id);
        return "detalheMensagemRecebida?faces-redirect=true";
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public ContatoDAO getContatoDAO() {
        return contatoDAO;
    }

    public void setContatoDAO(ContatoDAO contatoDAO) {
        this.contatoDAO = contatoDAO;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public int getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(int idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void setMensagensRecebidas(List<Contato> mensagensRecebidas) {
        this.mensagensRecebidas = mensagensRecebidas;
    }

    public void setMensagensEnviadas(List<Contato> mensagensEnviadas) {
        this.mensagensEnviadas = mensagensEnviadas;
    }

}
