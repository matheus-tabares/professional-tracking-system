/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Bruno
 */
@Entity
@Table
public class Profissional implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    
    @Length(max = 40, message = "Maximo 40 letras")
    @Pattern(regexp = "[a-zA-Z+ +']+", message = "Somente letras")
    @NotEmpty    
    private String nome;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Length(min = 4, max = 8, message = "Senha entre 4 e 8 caracteres")
    private String senha;

    @NotEmpty
    @CPF
    private String CPF;

    private String descricao;
    
    @Pattern(regexp = "[0-9]+", message = "Somente números e não vazio")
    private String telefoneResidencial;
    
    @Pattern(regexp = "[0-9]+", message = "Somente números e não vazio")
    private String telefoneCelular;

    @ManyToOne
    private Categoria categoria;

    @OneToOne
    private Endereco endereco;

    @OneToMany
    private List<Contato> mensagens;

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

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Contato> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Contato> mensagens) {
        this.mensagens = mensagens;
    }

}
