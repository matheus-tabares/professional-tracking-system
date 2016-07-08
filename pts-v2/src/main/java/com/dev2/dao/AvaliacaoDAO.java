/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.dao;

import com.dev2.model.Avaliacao;
import com.dev2.model.Usuario;
import com.dev2.util.Conexao;
import com.dev2.util.HibernateUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
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
        ArrayList<Avaliacao> resultado = (ArrayList<Avaliacao>) sessao.createQuery("FROM Avaliacao where idProfissional_id=:id").setInteger("id", id).list();
        
        System.out.println("Nº de avaliacoes : " + resultado.size());
        System.out.println("id consultado : " + id);
                
        return resultado;
    }
    
    public int minhasAvaliacoesRecebidas(int id) {
                       
        Query resultado2 = sessao.createQuery("select avg(valor)" + "from  Avaliacao where idProfissional_id=:id").setInteger("id", id);
        Double total = (Double) resultado2.uniqueResult();
        System.out.println("Id para visualizar avaliações: " + id);
        System.out.println("Media antes da conversão : " + total);
        int media = total.intValue();
        System.out.println("Media : " + media);
        
        return media;
    }
    
   /*
    public void mediaAvaliacoes(int id) {
        /* Query para trazer a média 
        Query resultado2 = sessao.createQuery("select avg(valor)" + "from  Avaliacao where idUsuario_id=:id").setInteger("id", id);
        Double total = (Double) resultado2.uniqueResult();
        System.out.println("tam id : " + total);
               
    }
    */
}
