package com.dev2.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Length(max = 40, message = "Maximo 40 letras")
    @Pattern(regexp = "[a-zA-Z+ +'+ã+Ã+ê+é+ó+ô+ç]+", message = "Somente letras")
    @NotEmpty  
    private String nome;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Length(min = 4, max = 8, message = "Senha entre 4 e 8 caracteres")
    private String senha;

    
    private String CPF;

    @OneToOne
    private Endereco endereco;
       
    private String telefoneResidencial;

    @Pattern(regexp = "[0-9]+", message = "Somente números e não vazio")
    private String telefoneCelular;

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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

    public List<Contato> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Contato> mensagens) {
        this.mensagens = mensagens;
    }
}
