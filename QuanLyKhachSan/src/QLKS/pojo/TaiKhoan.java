package QLKS.pojo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.persistence.*;
/**
 *
 * @author HP
 */
@Entity
@Table (name="taikhoan")
public class TaiKhoan {
    @Id
    @Column(name ="idNhanVien")
    private int idNhanVien;
    @Column(name ="taiKhoan")
    private String taiKhoan;
    @Column(name ="matKhau")
    private String matKhau;
    
    public TaiKhoan(){
        
    }

    /**
     * @return the idNhanVien
     */
    
    public int getIdNhanVien() {
        return idNhanVien;
    }

    /**
     * @param idNhanVien the idNhanVien to set
     */
    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    /**
     * @return the taiKhoan
     */
    public String getTaiKhoan() {
        return taiKhoan;
    }

    /**
     * @param taiKhoan the taiKhoan to set
     */
    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    /**
     * @return the matKhau
     */
    public String getMatKhau() {
        return matKhau;
    }

    /**
     * @param matKhau the matKhau to set
     */
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
}
