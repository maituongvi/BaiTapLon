/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;

import QLKS.pojo.KhachHang;
import QLKS.pojo.LoaiPhong;
import QLKS.pojo.Phong;
import java.util.List;
import javafx.scene.control.Alert;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import static quanlykhachsan.TimKiemPhongController.checkGiaPhong;
import static quanlykhachsan.TimKiemPhongController.checkLoaiPhong;
import static quanlykhachsan.TimKiemPhongController.checkMaPhong;
import static quanlykhachsan.TimKiemPhongController.checkSoNguoi;
import static quanlykhachsan.TimKiemPhongController.checkTinhTrangPhong;

/**
 *
 * @author HP
 */
public class Utils {
    public static final SessionFactory factory = HibernateUtil.getSessionFactory();
    public static String tieuDe = "";
    public static String noiDung = "";
    public static int dem = 0;
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
    
    @SuppressWarnings("empty-statement")
    public static List<Phong> laydsPhong(String loai, String soN, int check){
        Session session = factory.openSession();
        Criteria cr =session.createCriteria(Phong.class);
        int soNguoi = 0;
        Criterion tinhT =  Restrictions.eq("tinhTrangP", check);
        int loaiP = traloaiPhong(loai);
        
        
        if (check == -2){
            //hien thi tất cả
            Criterion phiaA =  Restrictions.eq("tinhTrangP", 1);
            Criterion phiaB =  Restrictions.eq("tinhTrangP", 0);
            cr.add(Restrictions.or(phiaA, phiaB));
        }
        else
            if (loaiP == 0 && "".equals(soN) ){
            
           if (cr.add(Restrictions.or(tinhT)).list().isEmpty())
                    {
                        tieuDe = "Thông báo ";
                        switch (check) {
                            case 0:
                                noiDung = " Hết phòng trống!";
                                break;
                            case -1:
                                tieuDe = "Nhắc nhở ";
                                noiDung = " Bạn cần nhập dữ liệu!";
                                break;
                           
                            default:
                                noiDung = " Không có phòng nào đã được đặt!";
                                break;
                        }
                        dem = -1;
                    }
           else 
                cr.add(Restrictions.or(tinhT));
        }
        else 
            if (!"".equals(soN) && loaiP == 0)
            {  
                soNguoi = Integer.parseInt(soN);
                Criterion nguoi = Restrictions.eq("sucChua", soNguoi);
                if (check == -1)
                    cr.add(Restrictions.or(nguoi));
                else
                    cr.add(Restrictions.and(nguoi, tinhT));
                        
            }else 
                if ("".equals(soN) && 0 != loaiP ){
                        Criterion l =  Restrictions.eq("loaiPhong", new LoaiPhong(loaiP, loai));
                    if (check == -1)
                        cr.add(Restrictions.or(l));
                    else
                        cr.add(Restrictions.and(l, tinhT));
                    
                }
                else {
                    Criterion l =  Restrictions.eq("loaiPhong", new LoaiPhong(loaiP, loai));
                    soNguoi = Integer.parseInt(soN);
                    Criterion nguoi = Restrictions.eq("sucChua", soNguoi);
                    if (check == -1)
                        cr.add(Restrictions.and(l, nguoi));
                    else
                        cr.add(Restrictions.and(l, tinhT, nguoi));
                }
        
                
        cr.addOrder(Order.asc("maPhong"));
        List<Phong> phongs = cr.list();
        if (cr.list().isEmpty() && (dem == 0 || (!"".equals(soN) && loaiP != 0)))
        {
            tieuDe = "Thông báo ";
            noiDung = " Không tồn tại phòng bạn muốn tìm!";
        }
        session.close();
         
        return phongs;
    }
    
    public static void CapNhatPhong(Phong phong){
        Session session = factory.openSession();
        
        Transaction trans = session.beginTransaction();
        session.saveOrUpdate(phong);
        trans.commit();
        
        session.close();
    }
    
    public static void xoaPhong(Phong phong){
        Session session = factory.openSession();
        
        Transaction trans = session.beginTransaction();
        session.delete(phong);
        trans.commit();
        
        session.close();
    }
    
    public static int traloaiPhong(String loai){
         int loaiP = 0;
        if (loai != null){
           switch(loai){
                case "A":
                    loaiP = 1;
                    break;
                case "B":
                    loaiP = 2;
                    break;
                case "C":
                    loaiP = 3;
                    break;
                case "D":
                    loaiP = 4;
                    break;
           }
        }
        return loaiP;
    }
    
    
}
