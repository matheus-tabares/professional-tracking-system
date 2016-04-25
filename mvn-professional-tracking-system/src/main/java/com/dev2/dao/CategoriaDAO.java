/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.dao;

import java.util.ArrayList;
import com.dev2.model.Categoria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.dev2.util.HibernateUtil;

/**
 *
 * @author Bruno
 */
public class CategoriaDAO {

    private Session sessao;

    public CategoriaDAO() {
        sessao = HibernateUtil.getSessionFactory().openSession();
    }
    
    public void incluir(Categoria c) {
        Transaction t = sessao.beginTransaction();
        sessao.save(c);
        t.commit();
    }

    public Categoria carregar(int id) {
        return (Categoria) sessao.get(Categoria.class, id);
    }

    public ArrayList<Categoria> listar() {
        return (ArrayList<Categoria>) sessao.createCriteria(Categoria.class).list();
    }

}
