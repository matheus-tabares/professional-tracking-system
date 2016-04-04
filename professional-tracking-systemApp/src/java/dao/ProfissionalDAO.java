/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Profissional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

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
    
}
