package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Configurator {
    public static SessionFactory get() {
        return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }
}
