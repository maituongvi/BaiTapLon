/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLKS.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "chitiethoadon")
public class ChiTietHoaDon implements Serializable {
     //quan he Many-to-one
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maPhong" )
    private Phong phong;

    
    
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maHD" )
    private HoaDon hd;
    @Column(name = "giaTien")
    private double giaTien;
    
    @Id
    @Column(name = "ngayDen")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ngayDen;
    @Column(name = "ngayDi")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ngayDi;
    
    public ChiTietHoaDon(Phong phong, HoaDon maHD, double giaTien, Date ngayDen, Date ngayDi){
        this.phong = phong;
        this.hd = maHD;
        this.giaTien = giaTien;
        this.ngayDen = ngayDen;
        this.ngayDi = ngayDi;
        
    }
    
    public ChiTietHoaDon(){
        
    }

    /**
     * @return the phong
     */
    public Phong getPhong() {
        return phong;
    }
    public int getMaPhong() {
        return phong.getMaPhong();
    }
    /**
     * @param phong the phong to set
     */
    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    /**
     * @return the hd
     */
    public HoaDon getMaHD() {
        return hd;
    }

    /**
     * @param maHD the hd to set
     */
    public void setMaHD(HoaDon maHD) {
        this.hd = maHD;
    }

    /**
     * @return the giaTien
     */
    public double getGiaTien() {
        return giaTien;
    }

    /**
     * @param giaTien the giaTien to set
     */
    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    /**
     * @return the ngayDen
     */
    public Date getNgayDen() {
        return ngayDen;
    }

    /**
     * @param ngayDen the ngayDen to set
     */
    public void setNgayDen(Date ngayDen) {
        this.ngayDen = ngayDen;
    }

    /**
     * @return the ngayDi
     */
    public Date getNgayDi() {
        return ngayDi;
    }

    /**
     * @param ngayDi the ngayDi to set
     */
    public void setNgayDi(Date ngayDi) {
        this.ngayDi = ngayDi;
    }
    
    
}
