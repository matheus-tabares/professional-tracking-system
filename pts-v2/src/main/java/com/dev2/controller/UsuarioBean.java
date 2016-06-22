/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.controller;

import com.dev2.dao.CategoriaDAO;
import com.dev2.dao.EnderecoDAO;
import com.dev2.dao.ProfissionalDAO;
import com.dev2.dao.SegurancaDAO;
import com.dev2.dao.UsuarioDAO;
import com.dev2.model.Categoria;
import com.dev2.model.Endereco;
import com.dev2.model.Profissional;
import com.dev2.model.Seguranca;
import com.dev2.model.Usuario;
import com.dev2.util.HashUtil;
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
public class UsuarioBean implements Serializable {

    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Endereco endereco = new Endereco();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();
    private Profissional profissional = new Profissional();
    private ProfissionalDAO profissionalDAO = new ProfissionalDAO();
    private Categoria categoria = new Categoria();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private Seguranca seguranca = new Seguranca();
    private SegurancaDAO segurancaDAO = new SegurancaDAO();
    private boolean checkProfissional = false;
    private int idCategoria;
    private List<Usuario> profissionaisPorCategoria = usuarioDAO.listarProfissionais();
    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    public String cadastrar() {
        this.usuarioDAO = new UsuarioDAO();
        this.usuario.setSenha(HashUtil.generateHash(this.usuario.getSenha(), this.seguranca.getSALT()));
        System.out.println("CADASTRAR HASH GERADO: " + this.usuario.getSenha());
        this.usuario.setEhProfissional(checkProfissional);
        this.usuarioDAO.cadastrar(usuario);
        this.seguranca.setUsuario(usuarioDAO.carregar(this.usuario.getId()));
        this.segurancaDAO.incluir(seguranca);
        this.endereco.setUsuario(usuarioDAO.carregar(this.usuario.getId()));
        this.enderecoDAO.incluir(endereco);
        if (checkProfissional) {
            this.profissional.setUsuario(usuarioDAO.carregar(this.usuario.getId()));
            this.profissional.setCategoria(categoriaDAO.carregar(idCategoria));
            this.profissionalDAO.incluir(profissional);
        }
        if (checkProfissional) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PROFISSIONAL CADASTRADO", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "USUARIO CADASTRADO", ""));
        }
        inicializarVariaveis();
        return "home?faces-redirect=true";
    }

    public void consultaProfissional(int idProfissionalSelecionado) {
        usuario = usuarioDAO.carregar(idProfissionalSelecionado);
    }

    public String iniciaAlteracaoProfissional(int idProfissionalSelecionado) {
        usuario = usuarioDAO.carregar(loginBean.getUsuario().getId());
                System.out.println("numero do boi" + usuario.getEndereco().getNumero());
        return "editarPerfil?faces-redirect=true";
    }

    public String deletar(int id) {
        this.usuarioDAO.deletar(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "USUARIO EXCLUIDO", ""));
        getListaUsuarios();
        return null;
    }

    public ArrayList<Usuario> getListaProfissionais() {
        this.usuarioDAO = new UsuarioDAO();
        return usuarioDAO.listarProfissionais();
    }

    /*public ArrayList<Usuario> getListaProfissionaisPorCategoria() {
     this.usuarioDAO = new UsuarioDAO();
     return usuarioDAO.listarProfissionaisPorCategoria(idCategoria);
     }*/
    public void buscaProfissionalPorCategoria() {
        profissionaisPorCategoria = null;
        profissionaisPorCategoria = usuarioDAO.listarProfissionaisPorCategoria(idCategoria);
        System.out.println("ID CATEGORIA: " + idCategoria);
    }

    public ArrayList<Usuario> getListaUsuarios() {
        this.usuarioDAO = new UsuarioDAO();
        return usuarioDAO.listarUsuarios();
    }

    public String alterarPerfil() {

        if (!loginBean.getSenhaAntiga().equals("") && !loginBean.getSenhaNova().equals("")) {
            try {
                if (loginBean.alteraSenha()) {
                    this.usuarioDAO.alterar(loginBean.getUsuario());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados do perfil atualizados com sucesso!", ""));
                    return "painelProfissional?faces-redirect=true";
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "VERIFIQUE OS DADOS PREENCHIDOS, SENHAS NÃO PODEM SER IGUAIS", ""));
                    return null;
                }
            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "VERIFIQUE OS DADOS PREENCHIDOS, SENHAS NÃO PODEM SER IGUAIS", ""));
                return null;
            }

        }
        return null;
    }

    public ArrayList<Usuario> getListaProfissionaistop10() {
        this.usuarioDAO = new UsuarioDAO();
        return usuarioDAO.listarProfissionaisTOP10();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
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

    public Seguranca getSeguranca() {
        return seguranca;
    }

    public void setSeguranca(Seguranca seguranca) {
        this.seguranca = seguranca;
    }

    public SegurancaDAO getSegurancaDAO() {
        return segurancaDAO;
    }

    public void setSegurancaDAO(SegurancaDAO segurancaDAO) {
        this.segurancaDAO = segurancaDAO;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public ArrayList<Categoria> getListaCategorias() {
        this.categoriaDAO = new CategoriaDAO();
        return categoriaDAO.listarCategorias();
    }

    public boolean isCheckProfissional() {
        return checkProfissional;
    }

    public void setCheckProfissional(boolean checkProfissional) {
        this.checkProfissional = checkProfissional;
    }

    public List<Usuario> getProfissionaisPorCategoria() {
        return profissionaisPorCategoria;
    }

    public void setProfissionaisPorCategoria(List<Usuario> profissionaisPorCategoria) {
        this.profissionaisPorCategoria = profissionaisPorCategoria;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public void inicializarVariaveis() {
        this.usuario = new Usuario();
        this.usuarioDAO = new UsuarioDAO();
        this.endereco = new Endereco();
        this.enderecoDAO = new EnderecoDAO();
        this.profissional = new Profissional();
        this.profissionalDAO = new ProfissionalDAO();
        this.categoria = new Categoria();
        this.categoriaDAO = new CategoriaDAO();
        this.seguranca = new Seguranca();
        this.segurancaDAO = new SegurancaDAO();
        this.idCategoria = 0;
        this.checkProfissional = false;
    }

    public String returnIndex() {
        inicializarVariaveis();
        return "home?faces-redirect=true";
    }

}
