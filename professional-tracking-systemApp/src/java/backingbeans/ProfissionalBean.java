/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbeans;

import dao.CategoriaDAO;
import dao.EnderecoDAO;
import dao.ProfissionalDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Categoria;
import model.Endereco;
import model.Profissional;

/**
 *
 * @author Bruno
 */
@ManagedBean
@SessionScoped
public class ProfissionalBean {

    private Profissional profissional = new Profissional();
    private ProfissionalDAO profissionalDAO = new ProfissionalDAO();
    private List<Profissional> listaProfissionais;
    private Categoria categoria = new Categoria();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private Endereco endereco = new Endereco();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();
    private int idCategoria;
    private int idEndereco;
    private boolean renderedEndereco;

    public ProfissionalBean() {
        listaProfissionais = profissionalDAO.listar();
    }

    public String consultaProfissional(Profissional p) {
        setProfissional(p);
        return "consultaProfissional";
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
        return null;
    }

    public String incluirCategoria() {
        this.categoriaDAO = new CategoriaDAO();
        this.categoriaDAO.incluir(categoria);
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, new FacesMessage("Categoria Cadastrada!", ""));
        this.categoria = new Categoria();
        return null;
    }

    public ArrayList<Categoria> getListaCategorias() {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.listar();
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

    public String buscaProfissionalPorCategoria(int idCategoria) {

        return "ConsultaProfissionalPorCategoria";
    }

    public List<Profissional> getListaProfissionais() {
        return listaProfissionais;
    }

    public void setListaProfissional(List<Profissional> listaProfissionais) {
        this.listaProfissionais = listaProfissionais;
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

    public EnderecoDAO getEnderecoDAO() {
        return enderecoDAO;
    }

    public void setEnderecoDAO(EnderecoDAO enderecoDAO) {
        this.enderecoDAO = enderecoDAO;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public boolean isRenderedEndereco() {
        return renderedEndereco;
    }

    public void inseriuCPF() {
        if (this.endereco.getCEP().equals("")) {
            this.renderedEndereco = false;
        } else {
            this.renderedEndereco = true;
        }
    }

}
