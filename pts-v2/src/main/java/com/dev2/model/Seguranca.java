/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.model;

import com.dev2.util.HashUtil;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Bruno
 */
@Entity
public class Seguranca implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private Usuario usuario = new Usuario();

    private String SALT;

    public Seguranca() {
        this.SALT = HashUtil.generateSalt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSALT() {
        return SALT;
    }

}
