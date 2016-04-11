/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Endereco;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

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
}
