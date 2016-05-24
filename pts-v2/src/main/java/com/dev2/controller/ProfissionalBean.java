/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.controller;

import com.dev2.dao.CategoriaDAO;
import com.dev2.dao.ProfissionalDAO;
import com.dev2.model.Categoria;
import com.dev2.model.Profissional;
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
public class ProfissionalBean implements Serializable {

    private Profissional profissional = new Profissional();
    private ProfissionalDAO profissionalDAO = new ProfissionalDAO();
    private Categoria categoria = new Categoria();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private int idCategoria;

    public String cadastrar() {
        this.profissionalDAO = new ProfissionalDAO();
        this.categoria = categoriaDAO.carregar(idCategoria);
        this.profissional.setCategoria(categoria);
        this.profissionalDAO.incluir(profissional);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PROFISSIONAL CADASTRADO", ""));
        this.profissional = new Profissional();
        return null;
    }

    public String deletar(int id) {
        this.profissionalDAO.deletar(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "PROFISSIONAL EXCLUIDO", ""));
        getListaProfissionais();
        return null;
    }

    public ArrayList<Profissional> getListaProfissionais() {
        this.profissionalDAO = new ProfissionalDAO();
        return profissionalDAO.listarProfissionais();
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public ProfissionalDAO getProfissionalDAO() {
        return profissionalDAO;
    }

    public void setProfissionalDAO(ProfissionalDAO profissionalDAO) {
        this.profissionalDAO = profissionalDAO;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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
