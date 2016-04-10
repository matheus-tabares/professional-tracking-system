/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingbeans;

import dao.ClienteDAO;
import dao.ContatoDAO;
import dao.ProfissionalDAO;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Contato;

/**
 *
 * @author Bruno
 */
@ManagedBean
@SessionScoped
public class ContatoBean {

    private int idProfissional;
    private int idCliente;
    private Contato contato = new Contato();
    private ContatoDAO contatoDAO = new ContatoDAO();
    private ProfissionalDAO profissionalDAO = new ProfissionalDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();

    public String enviarMensagem() {
        /*this.contatoDAO = new ContatoDAO();
        this.contato.setCliente(clienteDAO.carregar(idCliente));
        this.contato.setProfissional(profissionalDAO.carregar(idProfissional));
        this.contatoDAO.incluir(contato);

        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.addMessage(null, new FacesMessage("Mensagem Enviada!", ""));
        this.contato = new Contato();
        return null;*/
        return "Parabens?faces-redirect=true";
    }

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public ContatoDAO getContatoDAO() {
        return contatoDAO;
    }

    public void setContatoDAO(ContatoDAO contatoDAO) {
        this.contatoDAO = contatoDAO;
    }

    public ProfissionalDAO getProfissionalDAO() {
        return profissionalDAO;
    }

    public void setProfissionalDAO(ProfissionalDAO profissionalDAO) {
        this.profissionalDAO = profissionalDAO;
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public String iniciarContato() {
        return "FormularioContato?faces-redirect=true";
    }

}
