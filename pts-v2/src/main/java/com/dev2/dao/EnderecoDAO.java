/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.dao;

import com.dev2.model.Endereco;
import com.dev2.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Bruno
 */
public class EnderecoDAO {

    private final Session sessao;

    public EnderecoDAO() {
        this.sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public void incluir(Endereco e) {
        Transaction t = sessao.beginTransaction();
        sessao.save(e);
        t.commit();
    }

    public Endereco carregar(int id) {
        return (Endereco) sessao.get(Endereco.class, id);
    }

    public void alterar(Endereco e) {
        Transaction t = sessao.beginTransaction();
        sessao.update(e);
        t.commit();
    }

    public void deletar(int id) {
        Transaction t = sessao.beginTransaction();
        sessao.delete(carregar(id));
        t.commit();
    }

    public ArrayList<Endereco> listarEnderecos() {
        return (ArrayList<Endereco>) sessao.createCriteria(Endereco.class).list();
    }

}
