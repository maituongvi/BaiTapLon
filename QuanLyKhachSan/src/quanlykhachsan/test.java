/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;

import QLKS.pojo.KhachHang;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import static quanlykhachsan.Utils.factory;

/**
 *
 * @author HP
 */
public class test {

    public static void main(String[] args) throws ParseException {
        Session session = factory.openSession();
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
        Date ns2 =fm.parse("18-06-1999");
        KhachHang kh = new KhachHang("3e333e16-fa57-4cdf-b3bc-ef47b31afa68", "Mai Tường Vi", ns2, "Nữ", "0356847078");
        Transaction trans = session.beginTransaction();
        session.saveOrUpdate(kh);
        trans.commit();
        System.out.println("kq" +trans);
        session.close();
    }
}
