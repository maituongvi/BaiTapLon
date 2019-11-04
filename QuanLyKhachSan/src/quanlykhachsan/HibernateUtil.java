/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;


import QLKS.pojo.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author HP
 */
public class HibernateUtil {

    private static final SessionFactory FACTORY;

    static {
        Configuration configure = new Configuration();
        configure.configure("hibernate.cfg.xml");
        configure.addAnnotatedClass(TaiKhoan.class);
        configure.addAnnotatedClass(KhachHang.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                applySettings(configure.getProperties());
        FACTORY = configure.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }
}
