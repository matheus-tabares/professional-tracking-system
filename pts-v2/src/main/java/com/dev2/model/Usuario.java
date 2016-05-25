/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Bruno
 */
@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Length(min = 8, max = 100)
    private String nome;

    @Email
    private String email;

    @Length(min = 6)
    private String senha;

    private boolean administrador = false;
    
    private boolean ehProfissional;

    @OneToOne(mappedBy = "usuario")
    private Endereco endereco;

    //@Pattern(regexp = "[0-9]+")
    @Length(min = 8, max = 15)
    private String telefone;

    //@CPF
    private String CPF;

    @OneToOne(mappedBy = "usuario")
    private Profissional profissional;

    @OneToOne(mappedBy = "usuario")
    private Seguranca seguranca;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Seguranca getSeguranca() {
        return seguranca;
    }

    public void setSeguranca(Seguranca seguranca) {
        this.seguranca = seguranca;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }
    public boolean ehProfissional() {
        return this.ehProfissional;
    }

    public void setEhProfissional(boolean ehProfissional) {
        this.ehProfissional = ehProfissional;
    }

    
}
