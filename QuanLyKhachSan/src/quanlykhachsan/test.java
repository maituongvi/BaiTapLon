/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;


import QLKS.pojo.*;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Entity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 *
 * @author HP
 */
public class test {
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query q = session.createQuery("from TaiKhoan");
        List<TaiKhoan> a = q.list();
        a.forEach(e->{
            System.out.println(e.getIdNhanVien());
            System.out.println(e.getTaiKhoan());
            System.out.println(e.getMatKhau());
        });
        session.close();
    }
}
