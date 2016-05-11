package com.dev2.dao;

import java.util.ArrayList;
import java.util.List;
import com.dev2.model.Contato;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import com.dev2.util.HibernateUtil;

public class ContatoDAO {

    private final Session sessao;

    public ContatoDAO() {
        this.sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public void incluir(Contato c) {
        Transaction t = sessao.beginTransaction();
        sessao.save(c);
        t.commit();
    }

    public ArrayList<Contato> listarMensagensProfissional(int idProfissionalLogado) {
        System.out.println("idProfissionalLogado DAO: " + idProfissionalLogado);
        List mensagens = sessao.createCriteria(Contato.class)
                .add(Restrictions.eq("quemRecebe", "profissional"))
                .add(Restrictions.eq("profissional.id", idProfissionalLogado))
                .list();

        return (ArrayList<Contato>) mensagens;
    }

    public ArrayList<Contato> listarMensagensCliente(int idClienteLogado) {
        System.out.println("idClienteLogado DAO: " + idClienteLogado);
        List mensagens = sessao.createCriteria(Contato.class)
                .add(Restrictions.eq("quemRecebe", "cliente"))
                .add(Restrictions.eq("cliente.id", idClienteLogado))
                .list();

        return (ArrayList<Contato>) mensagens;
    }
}
