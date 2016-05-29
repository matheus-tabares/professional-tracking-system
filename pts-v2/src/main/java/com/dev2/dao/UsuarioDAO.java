/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev2.dao;

import com.dev2.model.Usuario;
import com.dev2.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Bruno
 */
public class UsuarioDAO {

    private final Session sessao;

    public UsuarioDAO() {
        this.sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public void cadastrar(Usuario usuario) {
        //usuario.setSALT(Calendar.getInstance().getTime().toString() + "!@#$%*");
        //usuario.setSenha(HashUtil.stringToMD5(usuario.getSenha() + usuario.getSALT()));
        Transaction t = sessao.beginTransaction();
        sessao.save(usuario);
        t.commit();
    }

    public Usuario carregar(int id) {
        return (Usuario) sessao.get(Usuario.class, id);
    }

    public ArrayList<Usuario> listarUsuarios() {
        return (ArrayList<Usuario>) sessao.createCriteria(Usuario.class).list();
    }

    public void alterar(Usuario u) {
        Transaction t = sessao.beginTransaction();
        sessao.clear(); 
        sessao.update(u);
        t.commit();
    }

    public void deletar(int id) {
        Transaction t = sessao.beginTransaction();
        sessao.delete(carregar(id));
        t.commit();
    }

    public Usuario autentica(String email, String senha) {
        System.out.println("EMAIL: " + email);
        System.out.println("SENHA: " + senha);
        return (Usuario) sessao.createQuery("FROM Usuario WHERE email=:email AND senha=:senha").setString("email", email).setString("senha", senha).uniqueResult();

    }

    public Usuario buscarPorEmail(String email) {
        System.out.println("EMAIL: " + email);
        return (Usuario) sessao.createQuery("FROM Usuario WHERE email=:email").setString("email", email).uniqueResult();
    }
    
    public ArrayList<Usuario> listarProfissionaisTOP10() {
        return (ArrayList<Usuario>) sessao.createQuery("FROM Usuario WHERE ehProfissional=1").setMaxResults(10).list();
    }

    public ArrayList<Usuario> listarProfissionais() {
        return (ArrayList<Usuario>) sessao.createQuery("FROM Usuario WHERE ehProfissional=1").list();
    }

    public ArrayList<Usuario> listarProfissionaisPorCategoria(int categoria) {

        ArrayList<Usuario> listaCompleta = listarProfissionais();
        ArrayList<Usuario> listaFiltrada = new ArrayList<>();

        if (categoria == 0) {
            return listaCompleta;
        }

        for (int i = 0; i < listaCompleta.size(); i++) {
            Usuario u = listaCompleta.get(i);

            if ((u.getProfissional().getCategoria().getId()) == categoria) {
                listaFiltrada.add(u);
            }
        }

        return listaFiltrada;
    }

}
