/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.dao;

import com.dev2.model.Avaliacao;
import com.dev2.model.Usuario;
import com.dev2.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Caue
 */
public class AvaliacaoDAO {
     private final Session sessao;

    public AvaliacaoDAO() {
        this.sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public void cadastrar(Avaliacao avaliacao) {
        
        Transaction t = sessao.beginTransaction();
        sessao.save(avaliacao);
        t.commit();
    }
    
    public Usuario carregar(int id) {
        return (Usuario) sessao.get(Usuario.class, id);
    }
    
    public Avaliacao carregarAvaliacao(int id) {
        return (Avaliacao) sessao.get(Avaliacao.class, id);
    }
    
    public ArrayList<Avaliacao> listarAvaliacoes() {
        return (ArrayList<Avaliacao>) sessao.createCriteria(Avaliacao.class).list();
    }
    
    public ArrayList<Avaliacao> listaAvaliacoesRecebidas(int id) {
        ArrayList<Avaliacao> resultado = (ArrayList<Avaliacao>) sessao.createQuery("FROM Avaliacao where idUsuario_id=:id").setInteger("id", id).list();
        System.out.println("tam avaliacao : " + resultado.size());
        
        return resultado;
    }
}
