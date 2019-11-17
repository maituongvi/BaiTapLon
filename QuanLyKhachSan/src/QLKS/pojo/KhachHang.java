/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLKS.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

/**
 *
 * @author HP
 */
@Entity
@Table (name="khachhang")
public class KhachHang {
    @Id
    @Column (name ="maKH")
    private String maKH;
    @Column (name ="tenKH")
    private String tenKH;
    @Column (name ="ngaySinh")
    private Date ngaySinh;
    @Column (name ="gioiTinh")
    private String gioiTinh;
    @Column (name ="sdt")
    private String sdt;
    public KhachHang(){
    
    }
   
    public KhachHang(String id,String ten, String gt, String sdt){
        this.maKH = id;
        this.tenKH = ten ;
    
        this.sdt =sdt;
        this.gioiTinh =gt;
    }
    public KhachHang(String id,String ten, Date ns, String gioiTinh, String sdt){
        this.maKH = id;
        this.tenKH = ten ;
        this.gioiTinh =gioiTinh;
        this.sdt =sdt;
        this.ngaySinh =ns;
    }

    /**
     * @return the maKH
     */
    public String getMaKH() {
        return maKH;
    }

    /**
     * @param maKH the maKH to set
     */
    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    /**
     * @return the tenKH
     */
    public String getTenKH() {
        return tenKH;
    }

    /**
     * @param tenKH the tenKH to set
     */
    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    /**
     * @return the ngaySinh
     */
    public Date getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    /**
     * @return the gioiTinh
     */
    public String getGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    /**
     * @return the sdt
     */
    public String getSdt() {
        return sdt;
    }

    /**
     * @param sdt the sdt to set
     */
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
        String m = String.format("+Mã: %s\n+Tên: %s\n+Ngày sinh: %s\n+Giới tính: %s\n"
                + "+Số điện thoại: %s", this.maKH,this.tenKH,fm.format(this.ngaySinh),this.gioiTinh,this.sdt);
        return m; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
