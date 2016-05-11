package com.dev2.dao;

import com.dev2.model.Endereco;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.dev2.util.HibernateUtil;

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
}
