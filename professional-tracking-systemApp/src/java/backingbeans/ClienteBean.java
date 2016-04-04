/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbeans;

import dao.ClienteDAO;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Cliente;

/**
 *
 * @author Bruno
 */
@ManagedBean
@SessionScoped
public class ClienteBean {

    private Cliente cliente = new Cliente();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private List<Cliente> listaClientes;

    public ClienteBean() {
        listaClientes = clienteDAO.listar();
    }

    public String incluir() {
        clienteDAO.incluir(cliente);
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, new FacesMessage("Cliente Cadastrado!", ""));
        this.cliente = new Cliente();
        return null;
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

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public String consultaCliente(int idClienteSelecionado) {
        cliente = clienteDAO.carregar(idClienteSelecionado);
        return "DetalheCliente";
    }

    public String removerCliente(int idCliente) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;
        clienteDAO.remover(idCliente);
        listaClientes = clienteDAO.listar();
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Cliente removido com sucesso!", "");
        context.addMessage(null, msg);
        return null;
    }

    public String iniciaAlteracaoCliente(int idClienteSelecionado) {
        cliente = clienteDAO.carregar(idClienteSelecionado);
        return "AlteraCliente";
    }

    public String alterarCliente() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg;
        clienteDAO.alterar(cliente);
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Cliente alterado com sucesso!", "");
        cliente = new Cliente();
        context.addMessage(null, msg);
        return "ConsultaCliente";
    }

}
