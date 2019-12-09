/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLKS.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table (name="hoadon")
public class HoaDon {
    //quan he Many-to-one
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maKH" )
    private KhachHang kh;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maNV" )
    private NhanVien nv;    
    @Id
    @Column(name = "maHD")
    private String maHD;
    @Column(name = "ngayXuatHD")
    private Date ngayXuatHD;
    @Column(name = "tongTien")
    private double tongTien;
    
    
    public HoaDon(){
        
    }
    public HoaDon(String maHD, NhanVien nv, KhachHang kh, Date ngayXuatHD, double tongTien){
        this.maHD = maHD;
        this.nv = nv;
        this.kh = kh;
        this.ngayXuatHD = ngayXuatHD;
        this.tongTien = tongTien;
    }

    /**
     * @return the kh
     */
    public KhachHang getKh() {
        return kh;
    }

    /**
     * @param kh the kh to set
     */
    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    /**
     * @return the nv
     */
    public NhanVien getNv() {
        return nv;
    }

    /**
     * @param nv the nv to set
     */
    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    /**
     * @return the maHD
     */
    public String getMaHD() {
        return maHD;
    }

    /**
     * @param maHD the maHD to set
     */
    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    /**
     * @return the ngayXuatHD
     */
    public Date getNgayXuatHD() {
        return ngayXuatHD;
    }

    /**
     * @param ngayXuatHD the ngayXuatHD to set
     */
    public void setNgayXuatHD(Date ngayXuatHD) {
        this.ngayXuatHD = ngayXuatHD;
    }

    /**
     * @return the tongTien
     */
    public double getTongTien() {
        return tongTien;
    }

    /**
     * @param tongTien the tongTien to set
     */
    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    
    
}
