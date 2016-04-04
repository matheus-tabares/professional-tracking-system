/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Bruno
 */
public class ClienteDAO {

    private final Session sessao;

    public ClienteDAO() {
        this.sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public void incluir(Cliente c) {
        Transaction t = sessao.beginTransaction();
        sessao.save(c);
        t.commit();
    }
}
