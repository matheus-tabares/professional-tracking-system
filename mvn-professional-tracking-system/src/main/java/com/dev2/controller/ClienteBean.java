package com.dev2.controller;

import com.dev2.dao.ClienteDAO;
import com.dev2.dao.EnderecoDAO;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.dev2.model.Cliente;
import com.dev2.model.Endereco;

@ManagedBean
@SessionScoped
public class ClienteBean {
    private Cliente cliente = new Cliente();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private Endereco endereco = new Endereco();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();
    private List<Cliente> listaClientes;
    private boolean renderedEndereco;
    private int idClienteLogado;

    public ClienteBean() {
        listaClientes = clienteDAO.listar();
    }

    public String incluir() {
        this.enderecoDAO = new EnderecoDAO();
        this.enderecoDAO.incluir(endereco);
        this.cliente.setEndereco(enderecoDAO.carregar(this.endereco.getId()));
        clienteDAO.incluir(cliente);
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, new FacesMessage("Cliente Cadastrado!", ""));
        this.cliente = new Cliente();
        this.endereco = new Endereco();
        listaClientes = clienteDAO.listar();
        return null;
    }
    
    public String consultaCliente(int idClienteSelecionado) {
        cliente = clienteDAO.carregar(idClienteSelecionado);
        return "DetalheCliente?faces-redirect=true";
    }

    public String removerCliente(int idCliente) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;
        clienteDAO.remover(idCliente);
        listaClientes = clienteDAO.listar();
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Cliente removido com sucesso!", "");
        context.addMessage(null, msg);
        listaClientes = clienteDAO.listar();
        return null;
    }

    public String iniciaAlteracaoCliente(int idClienteSelecionado) {
        cliente = clienteDAO.carregar(idClienteSelecionado);
        return "AlteraCliente?faces-redirect=true";
    }

    public String alterarCliente() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;
        enderecoDAO.alterar(endereco);
        clienteDAO.alterar(cliente);
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Cliente alterado com sucesso!", "");
        cliente = new Cliente();
        context.addMessage(null, msg);
        listaClientes = clienteDAO.listar();
        return "ConsultaCliente?faces-redirect=true";
    }

    public void novoCliente() {
        this.cliente = new Cliente();
    }

    
    public String paginaLogin() {
        return "LoginCliente?faces-redirect=true";
    }
    
    public Cliente getCliente() {return cliente;}
    public void setCliente(Cliente cliente) {this.cliente = cliente;}
    public ClienteDAO getClienteDAO() {return clienteDAO;}
    public void setClienteDAO(ClienteDAO clienteDAO) {this.clienteDAO = clienteDAO;}
    public List<Cliente> getListaClientes() {return listaClientes;}
    public void setListaClientes(List<Cliente> listaClientes) {this.listaClientes = listaClientes;}
    public Endereco getEndereco() {return endereco;}
    public void setEndereco(Endereco endereco) {this.endereco = endereco;}
    public EnderecoDAO getEnderecoDAO() {return enderecoDAO;}
    public void setEnderecoDAO(EnderecoDAO enderecoDAO) {this.enderecoDAO = enderecoDAO;}
    public boolean isRenderedEndereco() {return renderedEndereco;}
    public int getIdClienteLogado() {return idClienteLogado;}
    public void setIdClienteLogado(int idClienteLogado) {this.idClienteLogado = idClienteLogado;}
}
