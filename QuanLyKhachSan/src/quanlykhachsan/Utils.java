/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;

import QLKS.pojo.KhachHang;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author HP
 */
public class Utils {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();
    
    public static List<KhachHang> laydsKH(String kw, int limit){
        Session session = factory.openSession();
        Criteria cr =session.createCriteria(KhachHang.class);
        
        if(!kw.isEmpty()){
            cr.add(Restrictions.ilike("tenKH", String.format("%%%s%%", kw)));
        }
        List<KhachHang> kh =cr.list();
        session.close();
        return kh;
    }
    
    public static void CapNhatKhachHang(KhachHang kh){
        Session session = factory.openSession();
        
        Transaction trans = session.beginTransaction();
        session.saveOrUpdate(kh);
        trans.commit();
        
        
        session.close();
    }
    
    public static void xoaKhachHang(KhachHang kh){
        Session session = factory.openSession();
        
        Transaction trans = session.beginTransaction();
        session.delete(kh);
        trans.commit();
        
        session.close();
    }
}
