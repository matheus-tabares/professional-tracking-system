package com.dev2.model;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Categoria implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    
    @NotEmpty
    private String nome;
    
    @NotEmpty
    private String descricao;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}
}
