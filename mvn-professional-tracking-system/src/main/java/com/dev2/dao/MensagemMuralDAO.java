package com.dev2.dao;

import com.dev2.model.MensagemMural;
import com.dev2.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class MensagemMuralDAO {
    private final Session sessao;

    public MensagemMuralDAO() {
        this.sessao = HibernateUtil.getSessionFactory().openSession();
    }    
    
    public void publicar(MensagemMural mensagemMural) {
        Transaction t = sessao.beginTransaction();
        sessao.save(mensagemMural);
        t.commit();
    }
    
    public ArrayList<MensagemMural> listarPorCategoria(int idCategoria) {
        List mensagemMural = sessao.createCriteria(MensagemMural.class)
                .add(Restrictions.eq("categoria.id", idCategoria))
                .list();
        return (ArrayList<MensagemMural>) mensagemMural;
    }
}

