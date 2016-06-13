package com.dev2.dao;

import com.dev2.model.Mural;
import com.dev2.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class MuralDAO {

    private final Session sessao;

    public MuralDAO() {
        this.sessao = HibernateUtil.getSessionFactory().openSession();
    }

    public void incluir(Mural m) {
        Transaction t = sessao.beginTransaction();
        sessao.save(m);
        t.commit();
    }

    public List<Mural> listarPublicacoes(int idCategoria) {
        return (List<Mural>) sessao.createCriteria(Mural.class)
                .add(Restrictions.like("categoria.id", idCategoria))
                .list();
    }

    public Mural carregarPublicacao(int idPublicacao) {
        return (Mural) sessao.get(Mural.class, idPublicacao);
    }

    public List<Mural> minhasPublicacoes(int idUsuarioLogado) {
        System.out.println("ENTROU NO DAO COM ID:" + idUsuarioLogado);
        //return (List<Mural>) sessao.createCriteria(Mural.class)
        //        .add(Restrictions.like("usuarioQuePublicou.id", idUsuarioLogado));
        List<Mural> result = (List<Mural>) sessao.createQuery("from Mural where usuarioQuePublicou_id = :id")
                .setParameter("id", idUsuarioLogado)
                .list();
        return result;
    }

    public void excluir(Mural mural) {
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();
            sessao.delete(mural);
            transacao.commit();

        } catch (RuntimeException erro) {
            if (transacao != null) {
                transacao.rollback();
            }
            throw erro;
        } finally {
            sessao.close();
        }
    }
}
