package com.dev2.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sf;

    public static SessionFactory getSessionFactory() {
        if (sf == null) // Teremos apenas um SessionFactory para toda a aplicação.
        // cria session factory
        {
            sf = new Configuration().configure().buildSessionFactory();
        }
        return sf;
    }
}
