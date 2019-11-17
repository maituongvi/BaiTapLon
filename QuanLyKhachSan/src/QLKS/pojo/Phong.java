/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLKS.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "phong")
public class Phong implements Serializable {
    //quan he Many-to-one
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idLoaiP" )
    private LoaiPhong loaiPhong;

    @Id
    @Column(name = "maPhong")
    private int maPhong;
    @Column(name = "sucChua")
    private int sucChua;
    @Column(name = "giaP")
    private double giaP;
    @Column(name = "tinhTrangP")
    private int tinhTrangP;
    
    public Phong() {

    }

    public Phong(int maPhong, int sucChua,LoaiPhong loaiPhong, double giaP, int tinhTrangP) {
        this.maPhong = maPhong;
        this.sucChua = sucChua;
        this.giaP = giaP;
        this.tinhTrangP = tinhTrangP;
        this.loaiPhong = loaiPhong;
    }

    /**
     * @return the maPhong
     */
    public int getMaPhong() {
        return maPhong;
    }

    /**
     * @param maPhong the maPhong to set
     */
    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    /**
     * @return the sucChua
     */
    public int getSucChua() {
        return sucChua;
    }

    /**
     * @param sucChua the sucChua to set
     */
    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

   

    /**
     * @return the giaP
     */
    public String getGiaP() {
        return String.format("%2.0f%s", giaP, " VNĐ");
    }
    public double getGiaPhong() {
        return giaP;
    }
    /**
     * @param giaP the giaP to set
     */
    public void setGiaP(double giaP) {
        this.giaP = giaP;
    }

    /**
     * @return the tinhTrangP
     */
    public String getTinhTrangP() {
        if (tinhTrangP == 1)
            return "Đã đặt";
        else
            return "Trống";
    }
    public int getTinhTrangPhong() {
        return tinhTrangP;
    }
    /**
     * @param tinhTrangP the tinhTrangP to set
     */
    public void setTinhTrangP(int tinhTrangP) {
        this.tinhTrangP = tinhTrangP;
    }

    /**
     * @return the loaiPhong
     */
    public String getLoaiPhong() {
        return loaiPhong.getTenLoaiP();
    }

    /**
     * @param loaiPhong the loaiPhong to set
     */
    public void setLoaiPhong(LoaiPhong loaiPhong) {
        this.loaiPhong = loaiPhong;
    }


}
