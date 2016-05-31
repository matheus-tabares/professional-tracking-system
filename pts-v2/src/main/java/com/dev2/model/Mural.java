package com.dev2.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mural implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Usuario usuarioQuePublicou;

    @ManyToOne
    private Categoria categoria;

    private String titulo;

    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuarioQuePublicou() {
        return usuarioQuePublicou;
    }

    public void setUsuarioQuePublicou(Usuario usuarioQuePublicou) {
        this.usuarioQuePublicou = usuarioQuePublicou;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
