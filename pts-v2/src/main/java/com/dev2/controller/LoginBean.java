/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.controller;

import com.dev2.dao.UsuarioDAO;
import com.dev2.model.Usuario;
import com.dev2.util.HashUtil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Bruno
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private Usuario usuario;
    private String nomeUsuario;
    private String senha;
    private FacesContext context;
    private String senhaAntiga;
    private String senhaNova;
    private String confirmaSenhaNova;
    private String CPF;

    public String login() {
        context = FacesContext.getCurrentInstance();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (this.nomeUsuario.equals("") || this.senha.equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PREENCHA TODOS OS CAMPOS", ""));
            return null;
        }
        try {
            this.usuario = usuarioDAO.buscarPorEmail(this.nomeUsuario);
            String hash = usuario.getSeguranca().getSALT();
            String senhaCompleta = HashUtil.generateHash(this.senha, hash);
            this.usuario = usuarioDAO.autentica(nomeUsuario, senhaCompleta);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "DADOS INCORRETOS", ""));
            return null;
        }
        if (usuario != null) {
            System.out.println("USUARIO LOGADO: " + usuario.getEmail() + " ---- " + usuario.getNome());
            System.out.println("CEP E TEL: " + usuario.getEndereco().getCEP() + " ---- " + usuario.getEndereco().getUF() + " ---- " + usuario.getTelefone());
            return "/painelProfissional?faces-redirect=true";

        } else {
            System.out.println("LOGIN OU SENHA INVALIDOS");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "LOGIN OU SENHA INCORRETOS", ""));
            return null;
        }

    }

    public boolean alteraSenha() {

        try {
            String oldPassword = HashUtil.generateHash(this.senhaAntiga, this.usuario.getSeguranca().getSALT());
            String newPassword = HashUtil.generateHash(this.senhaNova, this.usuario.getSeguranca().getSALT());

            if (newPassword.equals(oldPassword)) {
                System.out.println("NÃO CONSEGUI ALTERAR A SENHA, SENHAS IGUAL A ATUAL");
                return false;
            }

            if (oldPassword.equals(this.usuario.getSenha())) {
                String newpass = HashUtil.generateHash(this.senhaNova, this.usuario.getSeguranca().getSALT());
                this.usuario.setSenha(newpass);
                System.out.println("SENHA ALTERADA - loginBean.alteraSenha()");
                return true;
            }
            System.out.println("NÃO CONSEGUI ALTERAR A SENHA");
            return false;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;

        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/home?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public FacesContext getContext() {
        return context;
    }

    public void setContext(FacesContext context) {
        this.context = context;
    }

    public String getSenhaAntiga() {
        return senhaAntiga;
    }

    public void setSenhaAntiga(String senhaAntiga) {
        this.senhaAntiga = senhaAntiga;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public void setSenhaNova(String senhaNova) {
        this.senhaNova = senhaNova;
    }

    public String tipoUsuario() {
        if (this.usuario != null) {
            return this.usuario.ehProfissional() ? "Profissional" : "Usuario";
        }
        return "";
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String forgotPassword() {
        try {
            UsuarioDAO userDAO = new UsuarioDAO();
            Usuario user = userDAO.buscarPorEmail(this.nomeUsuario);

            if (senhaNova.trim().equals("") || confirmaSenhaNova.trim().equals("") || nomeUsuario.trim().equals("") || CPF.trim().equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "SEM CAMPOS EM BRANCO, OK?", ""));
                return null;
            }

            if (!senhaNova.equals(confirmaSenhaNova)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "SENHAS NÃO CONFEREM", ""));
                inicializarVariaveis();
                return null;
            }

            if (this.nomeUsuario.equals(user.getEmail()) && this.CPF.equals(user.getCPF())) {
                if (HashUtil.generateHash(senhaNova, user.getSeguranca().getSALT()).equals(user.getSenha())) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "SUAS SENHAS SÃO IGUAIS", ""));
                    inicializarVariaveis();
                    return null;
                }

                String newPass = HashUtil.generateHash(this.senhaNova, user.getSeguranca().getSALT());
                user.setSenha(newPass);
                userDAO.alterar(user);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SENHA ALTERADA", ""));
            inicializarVariaveis();
            return null;

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "DADOS INVALIDOS", ""));
            inicializarVariaveis();
            return null;
        }
    }

    public String getConfirmaSenhaNova() {
        return confirmaSenhaNova;
    }

    public void setConfirmaSenhaNova(String confirmaSenhaNova) {
        this.confirmaSenhaNova = confirmaSenhaNova;
    }

    public void inicializarVariaveis() {
        this.senhaNova = "";
        this.nomeUsuario = "";
        this.CPF = "";
    }

    public void fileUpload(FileUploadEvent event) throws IOException {
        try {
            UploadedFile arq = event.getFile();

            InputStream in = new BufferedInputStream(arq.getInputstream());
            File file = new File("C:\\Users\\Mari\\Documents\\NetBeansProjects\\professional-tracking-system-with-photo\\pts-v2\\src\\main\\webapp\\resources\\img" + arq.getFileName());

            String caminho = "resources\\imagens\\" + arq.getFileName();

            FileOutputStream fout = new FileOutputStream(file);
            while (in.available() != 0) {
                fout.write(in.read());
            }
            usuario.setFoto(caminho);
            fout.close();

            FacesMessage msg = new FacesMessage("O Arquivo ", arq.getFileName() + " salvo em banco de dados.");
            FacesContext.getCurrentInstance().addMessage("msgUpdate", msg);

        } catch (Exception ex) {
            System.out.println("DEU ESTOURO NA IMAGEM LOGINBEAN");
        }
    }

    public boolean showProfissional() {
        if (this.usuario != null) {
            if (this.usuario.ehProfissional()) {
                return false;
            }
        }
        return true;
    }

    public String serProfissional() {
        return "tornarProfissional?faces-redirect=true";
    }
}
