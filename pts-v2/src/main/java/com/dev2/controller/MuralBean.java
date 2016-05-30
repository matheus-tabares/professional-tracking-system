/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.controller;

import com.dev2.dao.CategoriaDAO;
import com.dev2.dao.MuralDAO;
import com.dev2.model.Categoria;
import com.dev2.model.Mural;
import java.io.Serializable;
import java.util.ArrayList;
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
public class MuralBean implements Serializable {

    private Mural mural = new Mural();
    private MuralDAO muralDAO = new MuralDAO();
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;
    private int idProfissionalSelecionado;
    private List<Mural> listaDePublicacoes = muralDAO.listarPublicacoes();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private int idCategoria;

    public String cadastrar() {
        muralDAO = new MuralDAO();
        this.mural.setCategoria(categoriaDAO.carregar(idCategoria));
        this.mural.setUsuarioQuePublicou(loginBean.getUsuario());
        this.muralDAO.incluir(mural);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "TRABALHO CADASTRADO", this.mural.getTitulo()));
        this.mural = new Mural();
        return "painelProfissional?faces-redirect=true";
    }

    public ArrayList<Categoria> getListaCategorias() {
        this.categoriaDAO = new CategoriaDAO();
        return categoriaDAO.listarCategorias();
    }

    public List<Mural> geListaDePublicacoes() {
        return listaDePublicacoes;
    }

    public Mural getMural() {
        return mural;
    }

    public void setMural(Mural mural) {
        this.mural = mural;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public int getIdProfissionalSelecionado() {
        return idProfissionalSelecionado;
    }

    public void setIdProfissionalSelecionado(int idProfissionalSelecionado) {
        this.idProfissionalSelecionado = idProfissionalSelecionado;
    }

    public MuralDAO getMuralDAO() {
        return muralDAO;
    }

    public void setMuralDAO(MuralDAO muralDAO) {
        this.muralDAO = muralDAO;
    }

    public List<Mural> getListaDePublicacoes() {
        return listaDePublicacoes;
    }

    public void setListaDePublicacoes(List<Mural> listaDePublicacoes) {
        this.listaDePublicacoes = listaDePublicacoes;
    }

    public CategoriaDAO getCategoriaDAO() {
        return categoriaDAO;
    }

    public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

}
