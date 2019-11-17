/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLKS.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "loaiphong")
public class LoaiPhong implements Serializable {
    @Id
    @Column(name = "idLoaiP")
    private int idLoaiP;
    @Column(name = "tenLoaiP")
    private String tenLoaiP;
    
    public LoaiPhong(){
        
    }
    
    public LoaiPhong(int idLoaiP, String tenLoaiP){
        this.idLoaiP = idLoaiP;
        this.tenLoaiP = tenLoaiP;
    }

    /**
     * @return the idLoaiP
     */
    public int getIdLoaiP() {
        return idLoaiP;
    }

    /**
     * @param idLoaiP the idLoaiP to set
     */
    public void setIdLoaiP(int idLoaiP) {
        this.idLoaiP = idLoaiP;
    }

    /**
     * @return the loaiP
     */
    public String getTenLoaiP() {
        return tenLoaiP;
    }

    /**
     * @param tenLoaiP the loaiP to set
     */
    public void setTenLoaiP(String tenLoaiP) {
        this.tenLoaiP = tenLoaiP;
    }
    
    
}
