/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.util;

/**
 *
 * @author Bruno
 */
public class CódigosRefatorados {
    /*
    
    public String alterarPerfil2() {

        if (!loginBean.getSenhaAntiga().equals("") && !loginBean.getSenhaNova().equals("")) {
            loginBean.alteraSenha();
        }

        this.usuarioDAO.alterar(loginBean.getUsuario());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados do perfil atualizados com sucesso!", ""));
        return "painelProfissional?faces-redirect=true";
    }

    public String alterarPerfil() {
        System.out.println("<---------- ENTROU NO ALTERAR PERFIL ---------->");

        if (!loginBean.getSenhaAntiga().equals("") && !loginBean.getSenhaNova().equals("")) {
            System.out.println("<------ CAMPOS DE SENHA FORAM PREENCHIDOS, VOU TENTAR ALTERA-LOS ------>");
            try {
                System.out.println("<------ INICIO TRY ------>");
                if (loginBean.alteraSenha()) {
                    System.out.println("<------ CONSEGUI ALTERAR A SENHA ------>");
                    System.out.println("<---- VOU ALTERA O USUARIO " + loginBean.getUsuario().getEmail() + " ---->");
                    this.usuarioDAO.alterar(loginBean.getUsuario());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados do perfil atualizados com sucesso!", ""));
                    System.out.println("<---------- PELO VISTO FUNCIONOU ---------->");
                    return "painelProfissional?faces-redirect=true";
                } else {
                    System.out.println("NÃO CONSEGUI ALTERAR A SENHA");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "VERIFIQUE OS DADOS PREENCHIDOS, SENHAS NÃO PODEM SER IGUAIS", ""));
                    return null;
                }
            } catch (Exception ex) {
                System.out.println("NÃO CONSEGUI ALTERAR A SENHA");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "VERIFIQUE OS DADOS PREENCHIDOS, SENHAS NÃO PODEM SER IGUAIS", ""));
                return null;
            }

        } else {
            try {
                this.usuarioDAO.alterar(loginBean.getUsuario());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados atualizados com sucesso!", ""));
                return "painelProfissional?faces-redirect=true";
            } catch (Exception ex) {
                System.out.println("NAO FOI POSSIVEL ALTERAR, BRUNO MEXEU AQUI");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "BRUNO BUGOU TUDO!", ""));
                return null;
            }
        }
    }
    
    
        public void alteraSenha() {

     String toCompare = HashUtil.generateHash(this.senhaAntiga, this.usuario.getSeguranca().getSALT());
     String newpass;

     System.out.println("HASH ANTIGO : " + this.usuario.getSenha());
     System.out.println("HASH NOVO   : " + toCompare);
     if (toCompare.equals(this.usuario.getSenha())) {
     newpass = HashUtil.generateHash(this.senhaNova, this.usuario.getSeguranca().getSALT());
     this.usuario.setSenha(newpass);
     System.out.println("SENHA ALTERADA");
     }

     }
    
    
    
    
     */
}
