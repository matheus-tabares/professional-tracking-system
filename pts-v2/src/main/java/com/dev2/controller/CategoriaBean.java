/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.controller;

import com.dev2.dao.CategoriaDAO;
import com.dev2.model.Categoria;
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
public class CategoriaBean implements Serializable {

    private Categoria categoria = new Categoria();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    public String cadastrar() {
        this.categoriaDAO = new CategoriaDAO();
        this.categoriaDAO.incluir(categoria);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "CATEGORIA CADASTRADA", this.categoria.getNome()));
        this.categoria = new Categoria();
        return null;
    }

    public String deletar(int id) {
        this.categoriaDAO.deletar(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CATEGORIA EXCLUIDA", ""));
        getListaCategorias();
        return null;
    }

    public ArrayList<Categoria> getListaCategorias() {
        this.categoriaDAO = new CategoriaDAO();
        return categoriaDAO.listarCategorias();
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

}
