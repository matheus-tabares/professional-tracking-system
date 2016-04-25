/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.dao;

import java.util.ArrayList;
import java.util.List;
import com.dev2.model.Profissional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import com.dev2.util.HibernateUtil;

/**
 *
 * @author Bruno
 */
public class ProfissionalDAO {

    private final Session sessao;

    public ProfissionalDAO() {
        this.sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public void incluir(Profissional p) {
        Transaction t = sessao.beginTransaction();
        sessao.save(p);
        t.commit();
    }

    public Profissional carregar(int id) {
        return (Profissional) sessao.get(Profissional.class, id);
    }

    public ArrayList<Profissional> listar() {
        return (ArrayList<Profissional>) sessao.createCriteria(Profissional.class).list();
    }

    public ArrayList<Profissional> listarPorCategoria(int idCategoria) {

        List profissionais = sessao.createCriteria(Profissional.class)
                .add(Restrictions.eq("categoria.id", idCategoria))
                .list();        
        
        return (ArrayList<Profissional>) profissionais;
        
        /*String hql = "FROM Profissional p WHERE p.categoria.id =:categoria_id";
        Query query = sessao.createQuery(hql);
        query.setParameter("categoria_id", idCategoria);
        return (ArrayList<Profissional>) query.list();*/

    }

}
