/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.controller;

import com.dev2.dao.AvaliacaoDAO;
import com.dev2.dao.CategoriaDAO;
import com.dev2.dao.EnderecoDAO;
import com.dev2.dao.ProfissionalDAO;
import com.dev2.dao.SegurancaDAO;
import com.dev2.dao.UsuarioDAO;
import com.dev2.model.Avaliacao;
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
    private Avaliacao avaliacao = new Avaliacao();
    private AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
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
    private Integer valor;
    private List<Avaliacao> avaliacoesRecebidas;
    private int idProfissional;
    
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
            if (this.idCategoria == 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SELECIONE UMA CATEGORIA", ""));
                return null;
            }
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
    
    public String avaliar() {
       
       this.avaliacao.setIdUsuario(loginBean.getUsuario());
       this.avaliacao.setIdProfissional(avaliacaoDAO.carregar(idProfissional));
       this.avaliacao.setValor(valor);
       System.out.print("id do profissional: " + idProfissional);
       this.avaliacaoDAO.cadastrar(avaliacao);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVALIAÇÃO CADASTRADA", ""));
        this.valor = 0;
        this.avaliacao = new Avaliacao();
        return null;
    }

    public void consultaProfissional(int idProfissionalSelecionado) {
        idProfissional = idProfissionalSelecionado;
        usuario = usuarioDAO.carregar(idProfissionalSelecionado);
    }

    public String iniciaAlteracaoProfissional(int idProfissionalSelecionado) {
        System.out.println("<---------- INICIAR ALTERACAO ---------->");
        this.usuario = usuarioDAO.carregar(loginBean.getUsuario().getId());
        System.out.println("CARREGOU O USUARIO: " + usuario.getEmail());
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
        try {
            profissionaisPorCategoria = null;
            profissionaisPorCategoria = usuarioDAO.listarProfissionaisPorCategoria(idCategoria);
            System.out.println("ID CATEGORIA: " + idCategoria);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "NÃO EXISTEM PROFISSIONAIS CADASTRADOS!", ""));
        }
    }
    
    public ArrayList<Avaliacao> getListaAvaliacoes() {
        this.avaliacaoDAO = new AvaliacaoDAO();
        return avaliacaoDAO.listarAvaliacoes();
    }
    
    public List<Avaliacao> getAvaliacoesRecebidas() {
        return avaliacaoDAO.listaAvaliacoesRecebidas(loginBean.getUsuario().getId());
    }

    public ArrayList<Usuario> getListaUsuarios() {
        this.usuarioDAO = new UsuarioDAO();
        return usuarioDAO.listarUsuarios();
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
    
    public Integer getValor() {
        return valor;
    }
 
    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public AvaliacaoDAO getAvaliacaoDAO() {
        return avaliacaoDAO;
    }

    public void setAvaliacaoDAO(AvaliacaoDAO avaliacaoDAO) {
        this.avaliacaoDAO = avaliacaoDAO;
    }
    
    public int getIdProfissional() {
        return idProfissional;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    /**
     * @param avaliacoesRecebidas the avaliacoesRecebidas to set
     */
    public void setAvaliacoesRecebidas(List<Avaliacao> avaliacoesRecebidas) {
        this.avaliacoesRecebidas = avaliacoesRecebidas;
    }
    
    public void inicializarVariaveis() {
        this.usuario = new Usuario();
        this.usuarioDAO = new UsuarioDAO();
        this.avaliacao = new Avaliacao();
        this.avaliacaoDAO = new AvaliacaoDAO();
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

    public String queroSerProfissional() {
        try {
            System.out.println("ID CATEG--->" + idCategoria);
            if (idCategoria == 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SELECIONE UMA CATEGORIA", ""));
                return null;
            }
            this.usuario = loginBean.getUsuario();
            this.usuario.setEhProfissional(true);
            this.usuarioDAO.alterar(usuario);
            this.profissional = new Profissional();
            this.profissional.setUsuario(usuarioDAO.carregar(this.usuario.getId()));
            this.profissional.setCategoria(categoriaDAO.carregar(idCategoria));
            this.profissionalDAO.incluir(profissional);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "VIROU PROFISSIONAL", ""));
            return "painelProfissional?faces-redirect=true";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "NAO EH PROFISSIONAL", ""));
            return "";
        }
    }

    public boolean camposSenhaForamPreenchidos() {
        return !loginBean.getSenhaAntiga().trim().equals("") || !loginBean.getSenhaNova().trim().equals("");
    }

    public String alterarPerfil3() {
        System.out.println("<---------- ENTROU NO ALTERAR PERFIL ---------->");

        if (camposSenhaForamPreenchidos()) {
            System.out.println("<------ CAMPOS DE SENHA FORAM PREENCHIDOS, VOU TENTAR ALTERA-LOS ------>");
            try {
                System.out.println("<------ INICIO TRY ------>");
                if (loginBean.alteraSenha()) {
                    System.out.println("<------ CONSEGUI ALTERAR A SENHA ------>");
                    System.out.println("<---- VOU ALTERA O USUARIO " + loginBean.getUsuario().getEmail() + " ---->");
                    this.usuario = loginBean.getUsuario();
                    this.endereco = loginBean.getUsuario().getEndereco();
                    this.endereco.setUsuario(usuario);
                    this.enderecoDAO.alterar(endereco);
                    System.out.println("<------ CARREGOU ENDERECO ------>");
                    this.usuarioDAO.alterar(this.usuario);
                    loginBean.setUsuario(usuarioDAO.carregar(loginBean.getUsuario().getId()));
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PERFIL ATUALIZADO!", ""));
                    System.out.println("<---------- PELO VISTO FUNCIONOU ---------->");
                    return "painelProfissional?faces-redirect=true";
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "VERIFIQUE OS DADOS PREENCHIDOS, SENHAS NÃO PODEM SER IGUAIS", ""));
                }

            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ESTOURO NO TRY!", ""));
                return null;
            }
        } else { // SE OS CAMPOS SENHA NAO FORAM PREENCHIDOS
            try {
                System.out.println("<-------- TENTANDO ALTERAR USUÁRIO SEM ALTERAR SENHA -------->");
                this.usuario = loginBean.getUsuario();
                this.endereco = loginBean.getUsuario().getEndereco();
                this.endereco.setUsuario(usuario);
                this.enderecoDAO.alterar(endereco);
                System.out.println("<------ CARREGOU ENDERECO ------>");
                System.out.println("CARREGOU O USUARIO: " + this.usuario.getEmail());
                this.usuarioDAO.alterar(this.usuario);
                System.out.println("ALTEROU O USUARIO.");
                loginBean.setUsuario(usuarioDAO.carregar(loginBean.getUsuario().getId()));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PERFIL ATUALIZADO!", ""));
                return "painelProfissional?faces-redirect=true";

            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "DEU ERRO NO TRY SEM SENHA!", ""));
                return null;
            }
        }

        return null;
    }

}
