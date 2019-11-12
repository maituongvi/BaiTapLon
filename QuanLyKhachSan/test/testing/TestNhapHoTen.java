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
}
