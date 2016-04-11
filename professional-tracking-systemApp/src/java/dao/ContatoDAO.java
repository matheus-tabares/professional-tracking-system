/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Contato;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/**
 *
 * @author Bruno
 */
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

    public ArrayList<Contato> listarMensagensProfissional(int idProfissional) {

        List mensagens = sessao.createCriteria(Contato.class)
                .add(Restrictions.eq("profissional.id", idProfissional))
                .list();

        return (ArrayList<Contato>) mensagens;
    }

    public ArrayList<Contato> listarMensagensCliente(int idCliente) {

        List mensagens = sessao.createCriteria(Contato.class)
                .add(Restrictions.eq("cliente.id", idCliente))
                .list();

        return (ArrayList<Contato>) mensagens;
    }
}
