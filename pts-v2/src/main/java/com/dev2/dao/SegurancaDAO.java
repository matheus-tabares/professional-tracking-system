/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.dao;

import com.dev2.model.Seguranca;
import com.dev2.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Bruno
 */
public class SegurancaDAO implements Serializable {

    private final Session sessao;

    public SegurancaDAO() {
        sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public void incluir(Seguranca s) {
        Transaction t = sessao.beginTransaction();
        sessao.save(s);
        t.commit();
    }

    public Seguranca carregar(int id) {
        return (Seguranca) sessao.get(Seguranca.class, id);
    }

    public ArrayList<Seguranca> listarSegurancas() {
        return (ArrayList<Seguranca>) sessao.createCriteria(Seguranca.class).list();
    }

    public void deletar(int id) {
        Transaction t = sessao.beginTransaction();
        sessao.delete(carregar(id));
        t.commit();
    }
}
