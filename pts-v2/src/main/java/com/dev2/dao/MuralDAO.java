package com.dev2.dao;

import com.dev2.model.Mural;
import com.dev2.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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

    public ArrayList<Mural> listarPublicacoes(int idCategoria) {
        return (ArrayList<Mural>) sessao.createCriteria(Mural.class)
                .add(Restrictions.like("categoria.id", idCategoria))
                .list();
    }
    
    public Mural carregarPublicacao(int idPublicacao) {
        return (Mural) sessao.get(Mural.class, idPublicacao);
    }

}
