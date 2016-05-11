package com.dev2.dao;

import java.util.ArrayList;
import com.dev2.model.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.dev2.util.HibernateUtil;

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

    public ArrayList<Cliente> listar() {
        return (ArrayList<Cliente>) sessao.createCriteria(Cliente.class).list();
    }

    public Cliente carregar(int id) {
        return (Cliente) sessao.get(Cliente.class, id);
    }

    public void remover(int id) {
        Transaction t = sessao.beginTransaction();
        sessao.delete(carregar(id));
        t.commit();
    }

    public void alterar(Cliente c) {
        Transaction t = sessao.beginTransaction();
        sessao.clear();
        sessao.update(c);
        t.commit();
    }

    public Cliente autentica(String email, String senha) {
        System.out.println("EMAIL: " + email);
        System.out.println("SENHA: " + senha);
        return (Cliente) sessao.createQuery("FROM Cliente WHERE email=:email AND senha=:senha").setString("email", email).setString("senha", senha).uniqueResult();
                         
    }
}
