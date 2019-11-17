/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import org.junit.Assert;
import org.junit.Test;
import quanlykhachsan.NhapKhachHangController;

/**
 *
 * @author HP
 */
public class TestNhapHoTen {
    String hoTen;
    
    @Test
    public void test(){
        hoTen ="Mai Tường Vi ";
        
        boolean actual = NhapKhachHangController.checkNhapTenKhachHang(hoTen);
        System.out.println(actual);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    
    // nhập họ tên có kí tự số
    @Test
    public void test1(){
        hoTen ="Mai Tường Vi1 ";
        
        boolean actual = NhapKhachHangController.checkNhapTenKhachHang(hoTen);
        System.out.println(actual);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    
    // Nhập họ tên có kí tự đặc biệt
    @Test
    public void test2(){
        hoTen ="Mai Tường Vi %#@";
        
        boolean actual = NhapKhachHangController.checkNhapTenKhachHang(hoTen);
        System.out.println(actual);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
}
