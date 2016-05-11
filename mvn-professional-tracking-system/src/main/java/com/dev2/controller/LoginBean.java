package com.dev2.controller;

import com.dev2.dao.ClienteDAO;
import com.dev2.dao.ProfissionalDAO;
import com.dev2.model.Cliente;
import com.dev2.model.Profissional;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LoginBean {
    @ManagedProperty(value = "#{contatoBean}")
    private ContatoBean contatoBean;

    public ContatoBean getContatoBean() {
        return contatoBean;
    }

    public void setContatoBean(ContatoBean contatoBean) {
        this.contatoBean = contatoBean;
    }
    
    String email;
    String senha;
    int idProfissionalLogado;
    int idClienteLogado;
    int idUsuarioLogado;
    String tipoLogin = "C";
    
    Profissional profissional;
    Cliente cliente;

    public LoginBean() {
        this.profissional = new Profissional();
        this.cliente = new Cliente();
    }

    public String logar() {
        if (tipoLogin.equals("C")) {
            System.out.println("Logou como cliente");
            if (autenticarCliente()) {
                return "PainelCliente?faces-redirect=true";
            }
        } else if (tipoLogin.equals("P")) {
            System.out.println("Logou como Profissional");
            if (autenticarProfissional()) {
                return "PainelProfissional?faces-redirect=true";
            }
        } else {
            System.out.println("ERROUU");

        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha incorretos.", ""));
        return null;
    }

    public boolean autenticarCliente() {
        ClienteDAO clienteDAO = new ClienteDAO();
        cliente = clienteDAO.autentica(this.email, this.senha);
        this.email = null;
        this.senha = null;
        if (cliente == null) {

            System.out.println("FALHA LOGIN");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha incorretos.", ""));
            cliente = new Cliente();
            return false;

        } else {

            System.out.println("RODOU LOGIN");
            idClienteLogado = cliente.getId();
            System.out.println("idClienteLogado loginbean: " + idClienteLogado);
            System.out.println("cliente.getId() loginbean : " + cliente.getId());
            //contatoBean.setIdCliente(idClienteLogado);
            contatoBean.buscaMensagensCliente(idClienteLogado);
            idUsuarioLogado = idClienteLogado;
            System.out.println(idClienteLogado);
            return true;
        }
    }
    
    public boolean autenticarProfissional() {

        ProfissionalDAO profissionalDAO = new ProfissionalDAO();
        profissional = profissionalDAO.autentica(this.email, this.senha);
        this.email = null;
        this.senha = null;
        if (profissional == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha incorretos.", ""));
            profissional = new Profissional();
            System.out.println("FALHA LOGIN");
            return false;

        } else {

            System.out.println("RODOU LOGIN");
            idProfissionalLogado = profissional.getId();
            System.out.println("idProfissionalLogado loginbean: " + idProfissionalLogado);
            System.out.println("profissional.getId() loginbean : " + profissional.getId());
            //contatoBean.setIdProfissional(idProfissionalLogado);
            contatoBean.buscaMensagensProfissional(idProfissionalLogado);
            idUsuarioLogado = idProfissionalLogado;
            System.out.println(idProfissionalLogado);
            return true;
        }
    }

    public String logout() {
        this.cliente = new Cliente();
        this.profissional = new Profissional();
        this.idClienteLogado = 0;
        this.idProfissionalLogado = 0;
        return "index?faces-redirect=true";
    }

    @PreDestroy
    public void destructBean() {
        returnIndex();
    }

    public String returnIndex() {
        return "index?faces-redirect=true";
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdProfissionalLogado() {
        return idProfissionalLogado;
    }

    public void setIdProfissionalLogado(int idProfissionalLogado) {
        this.idProfissionalLogado = idProfissionalLogado;
    }

    public int getIdClienteLogado() {
        return idClienteLogado;
    }

    public void setIdClienteLogado(int idClienteLogado) {
        this.idClienteLogado = idClienteLogado;
    }

    public String getTipoLogin() {
        return tipoLogin;
    }

    public void setTipoLogin(String tipoLogin) {
        this.tipoLogin = tipoLogin;
    }

    public int getIdUsuarioLogado() {
        return idUsuarioLogado;
    }

    public void setIdUsuarioLogado(int idUsuarioLogado) {
        this.idUsuarioLogado = idUsuarioLogado;
    }

}
