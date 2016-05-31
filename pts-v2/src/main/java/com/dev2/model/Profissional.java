package com.dev2.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Profissional implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    //@Length(min = 5, max = 200)
    private String descricaoProfissional;

    @OneToOne
    private Usuario usuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescricaoProfissional() {
        return descricaoProfissional;
    }

    public void setDescricaoProfissional(String descricaoProfissional) {
        this.descricaoProfissional = descricaoProfissional;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
