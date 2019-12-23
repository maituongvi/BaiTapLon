/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykhachsan;

import QLKS.pojo.ChiTietHoaDon;
import QLKS.pojo.HoaDon;
import QLKS.pojo.KhachHang;
import QLKS.pojo.LoaiPhong;
import QLKS.pojo.NhanVien;
import QLKS.pojo.Phong;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author HP
 */
public class Utils {
    public static final SessionFactory factory = HibernateUtil.getSessionFactory();
    public static String tieuDe = "";
    public static String noiDung = "";
    public static int dem = 0;
    public static String content = "";
    public static String checkNhap = "";
    public static Phong ph;
    public static NhanVien nv;
    public static String id = "";
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
                Criterion nguoi = Restrictions.gt("sucChua", soNguoi);
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
                    Criterion nguoi = Restrictions.gt("sucChua", soNguoi);
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
    
      public static List<KhachHang> laydsKHBangSDT(String kw, int limit){
        Session session = factory.openSession();
        Criteria cr =session.createCriteria(KhachHang.class);
        
        if(!kw.isEmpty()){
            cr.add(Restrictions.ilike("sdt", kw));
        }
        
        
        List<KhachHang> kh =cr.list();
        session.close();
        return kh;
    }
    
    
    public static List<NhanVien> laydsNV(String kw, int limit){
        Session session = factory.openSession();
        Criteria cr =session.createCriteria(NhanVien.class);
        
        if(!kw.isEmpty()){
            cr.add(Restrictions.eq("idnhanVien", Integer.parseInt(kw)));
        }
        List<NhanVien> listNV =cr.list();
        session.close();
        return listNV;
    }
    
    
    public static List<HoaDon> laydsHD(String id, KhachHang kh){
        Session session = factory.openSession();
        Criteria cr =session.createCriteria(HoaDon.class);
        
        if(id != null){
            cr.add(Restrictions.and(Restrictions.ilike("maHD", id),Restrictions.eq("kh", kh) ));
        }
        List<HoaDon> hd =cr.list();
        session.close();
        return hd;
    }
    
    public static void CapNhatHD(HoaDon hd){
        Session session = factory.openSession();
        
        Transaction trans = session.beginTransaction();
        session.saveOrUpdate(hd);
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
    
    
     //check ngay
    public static boolean checkNgayHopLe(Date ngay){
        Date now = new Date();
        return ngay.before(now);
    }
   
  
    
     //check ngay start
    public static boolean checkNgayStart(Date ngayStart, Date ngayEnd) {
        boolean check = true;

        check = ngayStart.before(ngayEnd);
        if (check == false) {
            noiDung = " Ngày trả phòng phải sau ngày bắt đầu! ";
        }

        return check;
    }

    // hàm kiểm tra nhập số người hợp lệ
    public static boolean checkSoNguoi(String s){
        boolean check = false;
        Pattern pattern = Pattern.compile("^\\d{1,9}$");
        Matcher mat = pattern.matcher(s);
        if(mat.find()){
            check = true;
        }
        return check;
    }
    
    // hàm kiểm tra nhập số mã nhân viên hợp lệ
    public static boolean checkMaNV(String s){
        boolean check = false;
        Pattern pattern = Pattern.compile("^\\d{1,11}$");
        Matcher mat = pattern.matcher(s);
        if(mat.find()){
            check = true;
        }
        return check;
    }
    
    // hàm kiểm tra nhập số mã phòng
    public static boolean checkMaPhong(String s){
        boolean check = false;
        
        Pattern pattern = Pattern.compile("^\\d{1,17}$");
        Matcher mat = pattern.matcher(s);
        if(mat.find()){
           check = true;
        }
        return check;
    }
    
    // hàm kiểm tra nhập số mã phòng
    public static boolean checkGiaPhong(String s){
        boolean check = false;
        Pattern pattern = Pattern.compile("^\\d{1,170}$");
        Matcher mat = pattern.matcher(s);
        if(mat.find()){
            check = true;
        }
        return check;
    }
    
    // hàm kiểm tra nhập loại
    public static boolean checkLoaiPhong(String s){
        boolean check = false;
        if("A".equals(s) || "B".equals(s) || "C".equals(s) || "D".equals(s)) {
            check = true;
        }
        return check;
    }
    
    // hàm kiểm tra nhập tinh trang phong
    public static boolean checkTinhTrangPhong(boolean t, boolean f){
        boolean check = false;
        if((t && f == false )|| ( t == false && f)){
            check = true;
        }
        return check;
    }
    
    //kiem tra nhap
    public static boolean kiemTraNhapPhong(String ma, String soNguoi,String loai, String gia, boolean t, boolean f){
        boolean check = true;
        
        if(checkMaPhong(ma) == false){
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.setTitle("Lỗi nhập !!! ");
//            a.setContentText(" Vui lòng nhập lại mã hợp lệ.");
//            a.show();
            checkNhap = " Vui lòng nhập lại số phòng hợp lệ. ";
            check = false;
        }else
             if(checkSoNguoi(soNguoi) == false){
//                Alert a = new Alert(Alert.AlertType.ERROR);
//                a.setTitle("Lỗi nhập !!! ");
//                a.setContentText(" Vui lòng nhập lại số người hợp lệ. VD:2 ");
//                a.show();
                checkNhap = " Vui lòng nhập lại số người hợp lệ và không bỏ trống. VD:2 ";
                check = false;
            } else
                 if(checkLoaiPhong(loai) == false){
//                    Alert a = new Alert(Alert.AlertType.ERROR);
//                    a.setTitle("Lỗi nhập !!! ");
//                    a.setContentText(" Vui lòng chọn loại phòng hợp lệ.");
//                    a.show();
                    checkNhap = " Vui lòng chọn loại phòng hợp lệ và không bỏ trống. ";
                    check = false;
                }else
                     if(checkGiaPhong(gia) == false){
//                    Alert a = new Alert(Alert.AlertType.ERROR);
//                    a.setTitle("Lỗi nhập !!! ");
//                    a.setContentText(" Vui lòng chọn loại phòng hợp lệ.");
//                    a.show();
                    checkNhap = " Vui lòng nhập giá phòng hợp lệ và không bỏ trống. VD 1500000";
                    check = false;
                    } else
                         if(checkTinhTrangPhong(t, f) == false){
//                        Alert a = new Alert(Alert.AlertType.ERROR);
//                        a.setTitle("Lỗi nhập !!! ");
//                        a.setContentText(" Vui lòng chọn tinh trạng phòng ");
//                        a.show();
                        checkNhap = " Vui lòng chọn tinh trạng phòng ";
                        check = false;
                    }

                
                
                
        
           
        return check;
    }
     // hàm kiểm tra nhập số điện thoại hợp lệ
    public static boolean checkSDT(String s){
        boolean check = false;
        Pattern pattern = Pattern.compile("^0\\d{9}$");
        Matcher mat = pattern.matcher(s);
        if(mat.find()){
            check = true;
        }
        return check;
    }
    
    
    
    
    
    // hàm kiểm tra nhập trên khách hàng hợp lệ
    public static boolean checkNhapTenKhachHang(String s){
        boolean check = false;
        Pattern pattern = Pattern.compile("^[a-zA-z\\s\\p{L}]{3,40}$");
        Matcher mat = pattern.matcher(s);
        if(mat.find()){
            check = true;
        }
        return check;
    }
    
     // NhanVien
    // lấy danh sách nhân viên
    public static List<NhanVien> laydanhsachNV(String kw, int limit){
        Session session = factory.openSession();
        Criteria cr =session.createCriteria(NhanVien.class);
        
        if(!kw.isEmpty()){
            cr.add(Restrictions.ilike("tenNV", String.format("%%%s%%", kw)));
        }
        List<NhanVien> nv =cr.list();
        session.close();
        return nv;
    }
    
    //thêm hoặc cập nhật nhân viên
    public static void CapNhatNhanVien(NhanVien nv){
        Session session = factory.openSession();
        
        Transaction trans = session.beginTransaction();
        session.saveOrUpdate(nv);
        trans.commit();
        
        session.close();
    }
    
    // xóa nhân viên
    public static void xoaNhanVien(NhanVien nv){
        Session session = factory.openSession();
        
        Transaction trans = session.beginTransaction();
        session.delete(nv);
        trans.commit();
        
        session.close();
    }
    
    // check ngày đó đã có người đặt chưa?
    public static List<ChiTietHoaDon> checkNgayDat(Date ngayDen, Date ngayDi, Phong p){
        Session session = factory.openSession();
        org.hibernate.Query q =  session.createQuery("FROM ChiTietHoaDon A"
                + " WHERE A.phong = :room AND (( A.ngayDen < :ngay1 AND A.ngayDi > :ngay1 ) "
                + "OR ( A.ngayDen < :ngay2 AND A.ngayDi > :ngay2 ) OR "
                + "( A.ngayDen > :ngay1 AND A.ngayDi < :ngay2 ) )");
             
        q.setParameter("ngay1", ngayDen);
        q.setParameter("ngay2", ngayDi);
        q.setParameter("room",p);
        List<ChiTietHoaDon> list = q.list();
        return list;
        
    }
    
   
    
    
   
}
