package com.dev2.controller;

import com.dev2.dao.CategoriaDAO;
import com.dev2.dao.MuralDAO;
import com.dev2.dao.ProfissionalDAO;
import com.dev2.dao.UsuarioDAO;
import com.dev2.model.Categoria;
import com.dev2.model.Mural;
import com.dev2.model.Profissional;
import com.dev2.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class MuralBean implements Serializable {

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    private Mural mural = new Mural();
    private MuralDAO muralDAO = new MuralDAO();
    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private int idProfissionalSelecionado;
    private List<Mural> listaDePublicacoes;
    private int idCategoria;
    private Profissional profissional = new Profissional();
    private ProfissionalDAO profissionalDAO = new ProfissionalDAO();
    private Categoria categoria = new Categoria();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private int idPublicacao;
    private List<Mural> minhasPublicacoes;

    public String cadastrar() {
        muralDAO = new MuralDAO();
        usuario = new Usuario();
        usuarioDAO = new UsuarioDAO();

        this.mural.setCategoria(categoriaDAO.carregar(idCategoria));
        this.mural.setUsuarioQuePublicou(loginBean.getUsuario());
        this.muralDAO.incluir(mural);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PUBLICADO NO MURAL DE SERVIÇOS", this.mural.getTitulo()));
        this.mural = new Mural();

        return "painelProfissional?faces-redirect=true";
    }

    public String listaPublicacoes() {
        muralDAO = new MuralDAO();
        usuario = new Usuario();
        usuarioDAO = new UsuarioDAO();
        usuario = usuarioDAO.carregar(loginBean.getUsuario().getId());

        if (usuario.ehProfissional()) {
            profissional = profissionalDAO.carregar(usuario.getProfissional().getId());
            categoria = categoriaDAO.carregar(profissional.getCategoria().getId());
            listaDePublicacoes = muralDAO.listarPublicacoes(categoria.getId());
        }

        usuario = new Usuario();
        usuarioDAO = new UsuarioDAO();
        profissional = new Profissional();
        profissionalDAO = new ProfissionalDAO();
        categoria = new Categoria();
        categoriaDAO = new CategoriaDAO();
        muralDAO = new MuralDAO();

        return "muralDeServicos?faces-redirect=true";
    }

    public String minhasPublicacoes() {
        muralDAO = new MuralDAO();
        minhasPublicacoes = muralDAO.minhasPublicacoes(loginBean.getUsuario().getId());
        return "minhasPublicacoes?faces-redirect=true";
    }

    public String detalhesPublicacao(int idPublicacao) {
        mural = new Mural();
        muralDAO = new MuralDAO();
        mural = muralDAO.carregarPublicacao(idPublicacao);
        return "detalhesPublicacao?faces-redirect=true";
    }

    public void excluir(int id) {
        mural = new Mural();
        muralDAO = new MuralDAO();
        try {
            mural = muralDAO.carregarPublicacao(id);
            muralDAO.excluir(mural);
            minhasPublicacoes();
        } catch (RuntimeException erro) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "ERRO AO EXCLUIR A PUBLICACÃO", ""));

        }
    }

    public ArrayList<Categoria> getListaCategorias() {
        this.categoriaDAO = new CategoriaDAO();
        return categoriaDAO.listarCategorias();
    }

    public Mural getMural() {
        return mural;
    }

    public void setMural(Mural mural) {
        this.mural = mural;
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

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
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

    public int getIdPublicacao() {
        return idPublicacao;
    }

    public void setIdPublicacao(int idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

    public List<Mural> getMinhasPublicacoes() {
        return minhasPublicacoes;
    }

    public void setMinhasPublicacoes(List<Mural> minhasPublicacoes) {
        this.minhasPublicacoes = minhasPublicacoes;
    }

}
