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
import javax.persistence.ManyToOne;
import org.hibernate.validator.constraints.Length;

@Entity
public class Avaliacao implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    @Length(min = 8, max = 500)
    private String comentario;

    
    @ManyToOne
    private Usuario idUsuario;
    
    @ManyToOne
    private Usuario idProfissional;
    
    private int valor;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


    /**
     * @return the idProfissional
     */
    public Usuario getIdProfissional() {
        return idProfissional;
    }

    /**
     * @param idProfissional the idProfissional to set
     */
    public void setIdProfissional(Usuario idProfissional) {
        this.idProfissional = idProfissional;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @return the idUsuario
     */
    public Usuario getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
}
