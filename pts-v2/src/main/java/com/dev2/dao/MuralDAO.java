/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.dao;

import com.dev2.model.Mural;
import com.dev2.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Bruno
 */
public class MuralDAO {

    private final Session sessao;

    public MuralDAO() {
        this.sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public void incluir(Mural m) {
        Transaction t = sessao.beginTransaction();
        sessao.save(m);
        t.commit();
    }

    public ArrayList<Mural> listarPublicacoes() {
        return (ArrayList<Mural>) sessao.createCriteria(Mural.class).list();
    }

}
