/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.dao;

import com.dev2.model.Contato;
import com.dev2.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Bruno
 */
public class ContatoDAO {

    private final Session sessao;

    public ContatoDAO() {
        this.sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public void cadastrar(Contato c) {

        Transaction t = sessao.beginTransaction();
        sessao.save(c);
        t.commit();
    }

    public Contato carregar(int id) {
        return (Contato) sessao.get(Contato.class, id);
    }

    public ArrayList<Contato> listaMensagensEnviadas(int id) {

        return (ArrayList<Contato>) sessao.createQuery("FROM Contato where remetente_id=:id").setInteger("id", id).list();
    }
    
    public ArrayList<Contato> listaMensagensRecebidas(int id) {
        ArrayList<Contato> resultado = (ArrayList<Contato>) sessao.createQuery("FROM Contato where destinatario_id=:id").setInteger("id", id).list();
        System.out.println("TAMHO ARRAY: " + resultado.size());
        //return (ArrayList<Contato>) sessao.createQuery("FROM Contato where destinatario_id=:id").setInteger("id", id).list();
        return resultado;
    }

}
