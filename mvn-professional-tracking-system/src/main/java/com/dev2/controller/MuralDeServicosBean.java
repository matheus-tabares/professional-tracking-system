package com.dev2.controller;

import com.dev2.dao.CategoriaDAO;
import com.dev2.dao.ClienteDAO;
import com.dev2.dao.MensagemMuralDAO;
import com.dev2.dao.ProfissionalDAO;
import com.dev2.model.Cliente;
import com.dev2.model.MensagemMural;
import com.dev2.model.Profissional;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class MuralDeServicosBean {
    private MensagemMural mensagemMural = new MensagemMural();
    private MensagemMuralDAO mensagemMuralDAO = new MensagemMuralDAO();
    private int idCategoria = 1;
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private int idCliente = 1;
    private Cliente cliente = new Cliente();   
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ProfissionalBean profissionalBean = new ProfissionalBean();
    private List publicacoesPorCategoria;
    private LoginBean loginBean = new LoginBean();
    private ProfissionalDAO profissionalDAO = new ProfissionalDAO();

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public List getPublicacoesPorCategoria() {
        return publicacoesPorCategoria;
    }

    public void setPublicacoesPorCategoria(List publicacoesPorCategoria) {
        this.publicacoesPorCategoria = publicacoesPorCategoria;
    }

    public ProfissionalBean getProfissionalBean() {
        return profissionalBean;
    }

    public void setProfissionalBean(ProfissionalBean profissionalBean) {
        this.profissionalBean = profissionalBean;
    }

    public MensagemMural getMensagemMural() {
        return mensagemMural;
    }

    public void setMensagemMural(MensagemMural mensagemMural) {
        this.mensagemMural = mensagemMural;
    }

    public MensagemMuralDAO getMensagemMuralDAO() {
        return mensagemMuralDAO;
    }

    public void setMensagemMuralDAO(MensagemMuralDAO mensagemMuralDAO) {
        this.mensagemMuralDAO = mensagemMuralDAO;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public CategoriaDAO getCategoriaDAO() {
        return categoriaDAO;
    }

    public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
    
    public MuralDeServicosBean() {
        publicacoesPorCategoria = mensagemMuralDAO.listarPorCategoria(idCategoria);
    }
    
    public String publicar() {
        mensagemMural.setCategoria(categoriaDAO.carregar(idCategoria));
        mensagemMural.setCliente(clienteDAO.carregar(idCliente));
        mensagemMuralDAO.publicar(mensagemMural);
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, new FacesMessage("Publicado no Mural com sucesso!", ""));
        buscarPublicacaoMuralPorCategoria(idCategoria);
        mensagemMural = new MensagemMural();
        idCategoria = 0;
        idCliente = 0;
        return null;
    } 
    
    public List buscarPublicacaoMuralPorCategoria(int idCategoria) {
        //int idProfissionalLogado = loginBean.idProfissionalLogado;
        //Profissional profissional = profissionalDAO.carregar(idProfissionalLogado);
        publicacoesPorCategoria = mensagemMuralDAO.listarPorCategoria(idCategoria);
        return publicacoesPorCategoria;
    }
}
