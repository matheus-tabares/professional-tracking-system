/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbeans;

import dao.ClienteDAO;
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

}
