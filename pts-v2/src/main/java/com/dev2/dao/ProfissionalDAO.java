/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.dao;

import com.dev2.model.Profissional;
import com.dev2.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Bruno
 */
public class ProfissionalDAO {

    private final Session sessao;

    public ProfissionalDAO() {
        sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public void incluir(Profissional p) {
        Transaction t = sessao.beginTransaction();
        sessao.save(p);
        t.commit();
    }

    public Profissional carregar(int id) {
        return (Profissional) sessao.get(Profissional.class, id);
    }

    public ArrayList<Profissional> listarProfissionais() {
        return (ArrayList<Profissional>) sessao.createCriteria(Profissional.class).list();
    }

    public void alterar(Profissional p) {
        Transaction t = sessao.beginTransaction();
        sessao.update(p);
        t.commit();
    }

    public void deletar(int id) {
        Transaction t = sessao.beginTransaction();
        sessao.delete(carregar(id));
        t.commit();
    }

}
