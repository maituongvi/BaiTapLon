/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLKS.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author HP
 */
@Entity
@Table(name ="nhanvien")
public class NhanVien implements Serializable {
    @Id
    @Column (name ="idnhanVien")
    private int idnhanVien;
    @Column (name ="hoNV")
    private String hoNV;
    @Column (name ="tenNV")
    private String tenNV;
    @Column (name ="gioiTinh")
    private String gioiTinh;
    @Column (name ="queQuan")
    private String queQuan;
    @Column (name ="ngaySinh")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ngaySinh;
    @Column (name ="ngayVaoLam")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ngayVaoLam;
    @Column (name ="chucVu")
    private String chucVu;
    
    public NhanVien(){
        
    }
    public NhanVien(int id,String ho, String ten, String queQuan, Date ns, Date nvl, String gioiTinh, String chucVu){
        this.hoNV =ho;
        this.tenNV = ten;
        this.gioiTinh = gioiTinh;
        this.queQuan = queQuan;
        this.ngaySinh =ns;
        this.ngayVaoLam= nvl;
        this.chucVu =chucVu;
        this.idnhanVien =id;

    }
    
    public NhanVien( String ho, String ten, String queQuan, Date ns, Date nvl, String gioiTinh, String chucVu){
        this.hoNV =ho;
        this.tenNV = ten;
        this.gioiTinh = gioiTinh;
        this.queQuan = queQuan;
        this.ngaySinh =ns;
        this.ngayVaoLam= nvl;
        this.chucVu =chucVu;

    }

    /**
     * @return the idnhanVien
     */
    public int getIdnhanVien() {
        return idnhanVien;
    }

    /**
     * @param idnhanVien the idnhanVien to set
     */
    public void setIdnhanVien(int idnhanVien) {
        this.idnhanVien = idnhanVien;
    }

    /**
     * @return the hoNV
     */
    public String getHoNV() {
        return hoNV;
    }

    /**
     * @param hoNV the hoNV to set
     */
    public void setHoNV(String hoNV) {
        this.hoNV = hoNV;
    }

    /**
     * @return the tenNV
     */
    public String getTenNV() {
        return tenNV;
    }

    /**
     * @param tenNV the tenNV to set
     */
    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
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
     * @return the queQuan
     */
    public String getQueQuan() {
        return queQuan;
    }

    /**
     * @param queQuan the queQuan to set
     */
    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
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
     * @return the ngayVaoLam
     */
    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    /**
     * @param ngayVaoLam the ngayVaoLam to set
     */
    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    /**
     * @return the chucVu
     */
    public String getChucVu() {
        return chucVu;
    }

    /**
     * @param chucVu the chucVu to set
     */
    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
    
}
