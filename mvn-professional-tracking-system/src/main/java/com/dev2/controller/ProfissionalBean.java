package com.dev2.controller;

import com.dev2.dao.CategoriaDAO;
import com.dev2.dao.EnderecoDAO;
import com.dev2.dao.ProfissionalDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.dev2.model.Categoria;
import com.dev2.model.Endereco;
import com.dev2.model.Profissional;

@ManagedBean
@SessionScoped
public class ProfissionalBean {
    private Profissional profissional = new Profissional();
    private ProfissionalDAO profissionalDAO = new ProfissionalDAO();
    private List<Profissional> listaProfissionais;
    private List<Profissional> filtroCategoria;
    private Categoria categoria = new Categoria();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private Endereco endereco = new Endereco();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();
    private int idCategoria;
    private int idEndereco;
    private boolean renderedEndereco;
    private List<Profissional> profissionaisPorCategoria;
    private int idProfissionalLogado;

    public ProfissionalBean() {
        listaProfissionais = profissionalDAO.listar();
    }

    public String incluir() {
        this.categoriaDAO = new CategoriaDAO();
        this.enderecoDAO = new EnderecoDAO();
        this.profissional.setCategoria(categoriaDAO.carregar(this.idCategoria));
        this.enderecoDAO.incluir(endereco);
        this.profissional.setEndereco(enderecoDAO.carregar(this.endereco.getId()));
        this.profissionalDAO.incluir(profissional);
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, new FacesMessage("Profissional Cadastrado!", ""));
        this.profissional = new Profissional();
        this.endereco = new Endereco();
        this.categoria = new Categoria();
        this.idCategoria = 0;
        listaProfissionais = profissionalDAO.listar();
        return "index?faces-redirect=true";
    }

    public String incluirCategoria() {
        this.categoriaDAO = new CategoriaDAO();
        this.categoriaDAO.incluir(categoria);
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, new FacesMessage("Categoria Cadastrada!", ""));
        this.categoria = new Categoria();
        listaProfissionais = profissionalDAO.listar();
        return null;
    }

    public String consultaProfissional(int idProfissionalSelecionado) {
        profissional = profissionalDAO.carregar(idProfissionalSelecionado);
        return "DetalheProfissional?faces-redirect=true";
    }

    public ArrayList<Categoria> getListaCategorias() {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.listar();
    }

    
    public String buscaProfissionalPorCategoria() {
        profissionaisPorCategoria = profissionalDAO.listarPorCategoria(idCategoria);
        return "ConsultaProfissionalPorCategoria?faces-redirect=true";
    }
   
    public String paginaLogin() {
        return "LoginProfissional?faces-redirect=true";
    }

    public List<Profissional> getFiltroCategoria() {return filtroCategoria;}
    public void setFiltroCategoria(List<Profissional> filtroCategoria) {this.filtroCategoria = filtroCategoria;}     
    public Profissional getProfissional() {return profissional;}
    public void setProfissional(Profissional profissional) {this.profissional = profissional;}
    public ProfissionalDAO getProfissionalDAO() {return profissionalDAO;}
    public void setProfissionalDAO(ProfissionalDAO profissionalDAO) {this.profissionalDAO = profissionalDAO;}
    public List<Profissional> getListaProfissionais() {return listaProfissionais;}
    public void setListaProfissional(List<Profissional> listaProfissionais) {this.listaProfissionais = listaProfissionais;}
    public Categoria getCategoria() {return categoria;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}
    public CategoriaDAO getCategoriaDAO() {return categoriaDAO;}
    public void setCategoriaDAO(CategoriaDAO categoriaDAO) {this.categoriaDAO = categoriaDAO;}
    public int getIdCategoria() {return idCategoria;}
    public void setIdCategoria(int idCategoria) {this.idCategoria = idCategoria;}
    public EnderecoDAO getEnderecoDAO() {return enderecoDAO;}
    public void setEnderecoDAO(EnderecoDAO enderecoDAO) {this.enderecoDAO = enderecoDAO;}
    public Endereco getEndereco() {return endereco;}
    public void setEndereco(Endereco endereco) {this.endereco = endereco;}
    public int getIdEndereco() {return idEndereco;}
    public void setIdEndereco(int idEndereco) {this.idEndereco = idEndereco;}
    public boolean isRenderedEndereco() {return renderedEndereco;}
    public List<Profissional> getProfissionaisPorCategoria() {return profissionaisPorCategoria;}
    public void setProfissionaisPorCategoria(List<Profissional> profissionaisPorCategoria) {this.profissionaisPorCategoria = profissionaisPorCategoria;}
    public int getIdProfissionalLogado() {return idProfissionalLogado;}
    public void setIdProfissionalLogado(int idProfissionalLogado) {this.idProfissionalLogado = idProfissionalLogado;}
}
