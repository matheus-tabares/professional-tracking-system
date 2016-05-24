/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.controller;

import com.dev2.dao.EnderecoDAO;
import com.dev2.model.Endereco;
import java.io.Serializable;
import java.util.ArrayList;
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
public class EnderecoBean implements Serializable {

    private Endereco endereco = new Endereco();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();

    public String cadastrar() {
        this.enderecoDAO = new EnderecoDAO();
        this.enderecoDAO.incluir(endereco);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ENDERECO CADASTRADO", this.endereco.getBairro()));
        this.endereco = new Endereco();
        return null;
    }

    public String deletar(int id) {
        this.enderecoDAO.deletar(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ENDEREÇO EXCLUIDO", ""));
        getListaEnderecos();
        return null;
    }

    public ArrayList<Endereco> getListaEnderecos() {
        this.enderecoDAO = new EnderecoDAO();
        return enderecoDAO.listarEnderecos();
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public EnderecoDAO getEnderecoDAO() {
        return enderecoDAO;
    }

    public void setEnderecoDAO(EnderecoDAO enderecoDAO) {
        this.enderecoDAO = enderecoDAO;
    }

}
